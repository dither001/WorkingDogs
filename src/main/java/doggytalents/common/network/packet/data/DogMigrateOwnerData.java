package doggytalents.common.network.packet.data;

public class DogMigrateOwnerData extends DogData {

    public boolean confirmed;

    public DogMigrateOwnerData(int entityId, boolean confirmed) {
        super(entityId);
        this.confirmed = confirmed;
    }

    

}
