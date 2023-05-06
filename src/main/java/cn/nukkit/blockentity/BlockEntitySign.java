package cn.nukkit.blockentity;

import cn.nukkit.Player;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockSignPost;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DyeColor;
import cn.nukkit.utils.TextFormat;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class BlockEntitySign extends BlockEntitySpawnable {
    public static final String TAG_TEXT_BLOB = "Text";
    public static final String TAG_TEXT_LINE = "Text%d";
    public static final String TAG_TEXT_COLOR = "SignTextColor";
    public static final String TAG_GLOWING_TEXT = "IgnoreLighting";
    public static final String TAG_PERSIST_FORMATTING = "PersistFormatting";
    public static final String TAG_LEGACY_BUG_RESOLVE = "TextIgnoreLegacyBugResolved";
    public static final String TAG_FRONT_TEXT = "FrontText";
    public static final String TAG_BACK_TEXT = "BackText";
    public static final String TAG_WAXED = "IsWaxed";
    public static final String TAG_LOCKED_FOR_EDITING_BY = "LockedForEditingBy";

    private String[] frontText;
    private String[] backText;

    public BlockEntitySign(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Since("1.19.60-r1")
    @Override
    public void loadNBT() {
        super.loadNBT();
        frontText = new String[4];
        backText = new String[4];
        if (namedTag.containsCompound(TAG_FRONT_TEXT)) {
            getLines(true);
        } else {
            this.frontText[0] = "";
            this.namedTag.putCompound(new CompoundTag(TAG_FRONT_TEXT).putString(TAG_TEXT_BLOB, String.join("\n", new String[]{""})));
        }
        if (namedTag.containsCompound(TAG_BACK_TEXT)) {
            getLines(false);
        } else {
            this.backText[0] = "";
            this.namedTag.putCompound(new CompoundTag(TAG_BACK_TEXT).putString(TAG_TEXT_BLOB, String.join("\n", new String[]{""})));
        }

        // Check old text to sanitize
        if (frontText != null) {
            sanitizeText(frontText);
        }
        if (backText != null) {
            sanitizeText(backText);
        }
        if (!this.namedTag.getCompound(TAG_FRONT_TEXT).containsInt(TAG_TEXT_COLOR)) {
            this.setColor(true, DyeColor.BLACK.getSignColor());
        }
        if (!this.namedTag.getCompound(TAG_BACK_TEXT).containsInt(TAG_TEXT_COLOR)) {
            this.setColor(false, DyeColor.BLACK.getSignColor());
        }
        if (!this.namedTag.getCompound(TAG_FRONT_TEXT).containsByte(TAG_GLOWING_TEXT)) {
            this.setGlowing(true, false);
        }
        if (!this.namedTag.getCompound(TAG_BACK_TEXT).containsByte(TAG_GLOWING_TEXT)) {
            this.setGlowing(false, false);
        }
        updateLegacyCompoundTag();
    }

    @Override
    public void saveNBT() {
        super.saveNBT();
        this.namedTag.getCompound(TAG_FRONT_TEXT)
                .putString(TAG_TEXT_BLOB, String.join("\n", frontText))
                .putByte(TAG_PERSIST_FORMATTING, 1);
        //todo 由于1.19.80还没有更新sign新ui，所以暂时不启用反面文本
        /*this.namedTag.getCompound(TAG_BACK_TEXT)
                .putString(TAG_TEXT_BLOB, String.join("\n", backText))
                .putByte(TAG_PERSIST_FORMATTING, 1);*/
        this.namedTag.putBoolean(TAG_LEGACY_BUG_RESOLVE, true)
                .putByte(TAG_WAXED, 0)
                .putLong(TAG_LOCKED_FOR_EDITING_BY, getEditorEntityRuntimeId());
    }

    @Override
    public boolean isBlockEntityValid() {
        Block block = getBlock();
        return block instanceof BlockSignPost;
    }

    public boolean setText(String... lines) {
        return setText(true, lines);
    }

    /**
     * 设置lines文本数组到Sign对象，同时更新NBT
     *
     * @param front the front
     * @param lines the lines
     * @return the text
     */
    public boolean setText(boolean front, String... lines) {
        if (front) {
            for (int i = 0; i < 4; i++) {
                if (i < lines.length)
                    frontText[i] = lines[i];
                else
                    frontText[i] = "";
            }
            this.namedTag.getCompound(TAG_FRONT_TEXT).putString(TAG_TEXT_BLOB, String.join("\n", lines));
        } else {
            for (int i = 0; i < 4; i++) {
                if (i < lines.length)
                    backText[i] = lines[i];
                else
                    backText[i] = "";
            }
            this.namedTag.getCompound(TAG_BACK_TEXT).putString(TAG_TEXT_BLOB, String.join("\n", lines));
        }
        this.spawnToAll();
        if (this.chunk != null) {
            setDirty();
        }
        return true;
    }


    public String[] getText() {
        return getText(true);
    }

    public String[] getText(boolean front) {
        return front ? frontText : backText;
    }

    /**
     * 设置编辑此告示牌的玩家的运行时实体 ID。只有此玩家才能编辑告示牌。这用于防止多个玩家同时编辑同一告示牌，并防止玩家编辑他们未放置的告示牌。
     * <p>
     * Sets the runtime entity ID of the player editing this sign. Only this player will be able to edit the sign.
     * This is used to prevent multiple players from editing the same sign at the same time, and to prevent players
     * from editing signs they didn't place.
     */
    public long getEditorEntityRuntimeId() {
        return this.namedTag.getLong(TAG_LOCKED_FOR_EDITING_BY);
    }

    public void setEditorEntityRuntimeId(Long editorEntityRuntimeId) {
        this.namedTag.putLong(TAG_LOCKED_FOR_EDITING_BY, editorEntityRuntimeId == null ? -1L : editorEntityRuntimeId);
    }

    public BlockColor getColor() {
        return getColor(true);
    }

    public BlockColor getColor(boolean front) {
        if (front) {
            return new BlockColor(this.namedTag.getCompound(TAG_FRONT_TEXT).getInt(TAG_TEXT_COLOR), true);
        } else {
            return new BlockColor(this.namedTag.getCompound(TAG_BACK_TEXT).getInt(TAG_TEXT_COLOR), true);
        }
    }

    public void setColor(BlockColor color) {
        setColor(true, color);
    }

    public void setColor(boolean front, BlockColor color) {
        if (front) {
            this.namedTag.getCompound(TAG_FRONT_TEXT).putInt(TAG_TEXT_COLOR, color.getARGB());
        } else {
            this.namedTag.getCompound(TAG_BACK_TEXT).putInt(TAG_TEXT_COLOR, color.getARGB());
        }
    }

    public boolean isGlowing() {
        return isGlowing(true);
    }

    public boolean isGlowing(boolean front) {
        if (front) {
            return this.namedTag.getCompound(TAG_FRONT_TEXT).getBoolean(TAG_GLOWING_TEXT);
        } else {
            return this.namedTag.getCompound(TAG_BACK_TEXT).getBoolean(TAG_GLOWING_TEXT);
        }
    }

    public void setGlowing(boolean glowing) {
        setGlowing(true, glowing);
    }

    public void setGlowing(boolean front, boolean glowing) {
        if (front) {
            this.namedTag.getCompound(TAG_FRONT_TEXT).putBoolean(TAG_GLOWING_TEXT, glowing);
        } else {
            this.namedTag.getCompound(TAG_BACK_TEXT).putBoolean(TAG_GLOWING_TEXT, glowing);
        }
    }

    @Override
    public boolean updateCompoundTag(CompoundTag nbt, Player player) {
        if (!nbt.getString("id").equals(BlockEntity.SIGN)) {
            return false;
        }
        String[] lines = new String[4];
        Arrays.fill(lines, "");
        String[] splitLines = nbt.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT_BLOB).split("\n", 4);
        System.arraycopy(splitLines, 0, lines, 0, splitLines.length);

        sanitizeText(lines);

        SignChangeEvent signChangeEvent = new SignChangeEvent(this.getBlock(), player, lines);

        if (!this.namedTag.contains(TAG_LOCKED_FOR_EDITING_BY) || !Objects.equals(player.getId(), this.getEditorEntityRuntimeId())) {
            signChangeEvent.setCancelled();
        }

        if (player.getRemoveFormat()) {
            for (int i = 0; i < lines.length; i++) {
                lines[i] = TextFormat.clean(lines[i]);
            }
        }

        this.server.getPluginManager().callEvent(signChangeEvent);

        if (!signChangeEvent.isCancelled()) {
            this.setText(signChangeEvent.getLines());
            this.setEditorEntityRuntimeId(null);
            return true;
        }

        return false;
    }

    @Override
    public CompoundTag getSpawnCompound() {
        return new CompoundTag()
                .putString("id", BlockEntity.SIGN)
                .putCompound(new CompoundTag(TAG_FRONT_TEXT)
                        .putString(TAG_TEXT_BLOB, this.namedTag.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT_BLOB))
                        .putInt(TAG_TEXT_COLOR, this.getColor(true).getARGB())
                        .putBoolean(TAG_GLOWING_TEXT, this.isGlowing())
                        .putBoolean(TAG_PERSIST_FORMATTING, true)
                )
                //todo 由于1.19.80还没有更新sign新ui，所以暂时不启用反面文本
                /*.putCompound(new CompoundTag(TAG_BACK_TEXT)
                        .putString(TAG_TEXT_BLOB, this.namedTag.getCompound(TAG_BACK_TEXT).getString(TAG_TEXT_BLOB))
                        .putInt(TAG_TEXT_COLOR, this.getColor(false).getARGB())
                        .putBoolean(TAG_GLOWING_TEXT, this.isGlowing(false))
                        .putBoolean(TAG_PERSIST_FORMATTING, true)
                )*/
                .putBoolean(TAG_LEGACY_BUG_RESOLVE, true)
                .putInt("x", (int) this.x)
                .putInt("y", (int) this.y)
                .putInt("z", (int) this.z)
                .putByte(TAG_WAXED, 0)
                .putLong(TAG_LOCKED_FOR_EDITING_BY, getEditorEntityRuntimeId());
    }

    //读取指定面的NBT到Sign对象字段
    private void getLines(boolean front) {
        if (front) {
            String[] lines = this.namedTag.getCompound(TAG_FRONT_TEXT).getString(TAG_TEXT_BLOB).split("\n", 4);
            for (int i = 0; i < frontText.length; i++) {
                if (i < lines.length)
                    frontText[i] = lines[i];
                else
                    frontText[i] = "";
            }
        } else {
            String[] lines = this.namedTag.getCompound(TAG_BACK_TEXT).getString(TAG_TEXT_BLOB).split("\n", 4);
            for (int i = 0; i < backText.length; i++) {
                if (i < lines.length)
                    backText[i] = lines[i];
                else
                    backText[i] = "";
            }
        }
    }

    //在1.19.80以后,sign变成了双面显示，NBT结构也有所改变，这个方法将1.19.70以前的NBT更新至最新NBT结构
    private void updateLegacyCompoundTag() {
        if (this.namedTag.contains(TAG_TEXT_BLOB)) {
            String[] lines = namedTag.getString(TAG_TEXT_BLOB).split("\n", 4);
            for (int i = 0; i < frontText.length; i++) {
                if (i < lines.length)
                    frontText[i] = lines[i];
                else
                    frontText[i] = "";
            }
            this.namedTag.getCompound(TAG_FRONT_TEXT).putString(TAG_TEXT_BLOB, String.join("\n", frontText));
            this.namedTag.remove(TAG_TEXT_BLOB);
        } else {
            int count = 0;
            for (int i = 1; i <= 4; i++) {
                String key = TAG_TEXT_BLOB + i;
                if (namedTag.contains(key)) {
                    String line = namedTag.getString(key);
                    this.frontText[i - 1] = line;
                    this.namedTag.remove(key);
                    count++;
                }
            }
            if (count == 4) {
                this.namedTag.getCompound(TAG_FRONT_TEXT).putString(TAG_TEXT_BLOB, String.join("\n", frontText));
            }
        }
        if (this.namedTag.contains(TAG_GLOWING_TEXT)) {
            this.setGlowing(true, this.namedTag.getBoolean(TAG_GLOWING_TEXT));
            this.namedTag.remove(TAG_GLOWING_TEXT);
        }
        if (this.namedTag.contains(TAG_TEXT_COLOR)) {
            this.setColor(true, new BlockColor(this.namedTag.getInt(TAG_TEXT_COLOR), true));
            this.namedTag.remove(TAG_TEXT_COLOR);
        }

        //todo 由于1.19.80还没有更新sign新ui，所以暂时不启用反面文本
        if (this.namedTag.contains(TAG_BACK_TEXT)) {
            this.namedTag.remove(TAG_BACK_TEXT);
        }
        this.namedTag.remove("Creator");
    }

    //验证Line Text是否符合要求
    private static void sanitizeText(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            // Don't allow excessive text per line.
            if (lines[i] != null) {
                lines[i] = lines[i].substring(0, Math.min(255, lines[i].length()));
            }
        }
    }
}
