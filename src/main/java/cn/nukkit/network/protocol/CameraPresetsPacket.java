package cn.nukkit.network.protocol;

import cn.nukkit.api.PowerNukkitXOnly;
import cn.nukkit.api.Since;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.ByteOrder;

@Since("1.19.70-r1")
@PowerNukkitXOnly
@Getter
@Setter
public class CameraPresetsPacket extends DataPacket {
    private CompoundTag data;

    @Override
    public byte pid() {
        return ProtocolInfo.CAMERA_PRESETS_PACKET;
    }

    @Override
    public void decode() {
        this.data = this.getTag();
    }

    @Override
    public void encode() {
        this.reset();
        try {
            this.put(NBTIO.write(this.data, ByteOrder.LITTLE_ENDIAN, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
