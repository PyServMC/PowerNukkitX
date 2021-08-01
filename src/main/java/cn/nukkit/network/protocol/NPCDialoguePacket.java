/*
 * https://PowerNukkit.org - The Nukkit you know but Powerful!
 * Copyright (C) 2021  José Roberto de Araújo Júnior
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cn.nukkit.network.protocol;

import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;

import javax.annotation.Nonnull;

/**
 * @author joserobjr
 * @since 2021-07-06
 */
@PowerNukkitOnly
@Since("FUTURE")
public class NPCDialoguePacket extends DataPacket {
    @PowerNukkitOnly
    @Since("FUTURE")
    public static final byte NETWORK_ID = ProtocolInfo.NPC_DIALOGUE_PACKET;
    
    private static final NPCDialogAction[] ACTIONS = NPCDialogAction.values();
    
    private long uniqueEntityId;
    private NPCDialogAction action = NPCDialogAction.OPEN;
    private String dialogue = "";
    private String sceneName = "";
    private String npcName = "";
    private String actionJson = "";

    @PowerNukkitOnly
    @Since("FUTURE")
    public NPCDialoguePacket() {
        // Indicates when this public constructor were accessible
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        uniqueEntityId = getLLong();
        action = ACTIONS[getVarInt()];
        dialogue = getString();
        sceneName = getString();
        npcName = getString();
        actionJson = getString();
    }

    @Override
    public void encode() {
        reset();
        putLLong(uniqueEntityId);
        putVarInt(action.ordinal());
        putString(dialogue);
        putString(sceneName);
        putString(npcName);
        putString(actionJson);
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public long getUniqueEntityId() {
        return uniqueEntityId;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public void setUniqueEntityId(long runtimeEntityId) {
        this.uniqueEntityId = runtimeEntityId;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    @Nonnull
    public NPCDialogAction getAction() {
        return action;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public void setAction(@Nonnull NPCDialogAction action) {
        this.action = action;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    @Nonnull
    public String getDialogue() {
        return dialogue;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public void setDialogue(@Nonnull String dialogue) {
        this.dialogue = dialogue;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    @Nonnull
    public String getSceneName() {
        return sceneName;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public void setSceneName(@Nonnull String sceneName) {
        this.sceneName = sceneName;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    @Nonnull
    public String getNpcName() {
        return npcName;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public void setNpcName(@Nonnull String npcName) {
        this.npcName = npcName;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    @Nonnull
    public String getActionJson() {
        return actionJson;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public void setActionJson(@Nonnull String actionJson) {
        this.actionJson = actionJson;
    }

    @PowerNukkitOnly
    @Since("FUTURE")
    public enum NPCDialogAction {
        @PowerNukkitOnly @Since("FUTURE") OPEN,
        @PowerNukkitOnly @Since("FUTURE") CLOSE
    }
}
