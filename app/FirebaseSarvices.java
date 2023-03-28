
public class FirebaseSarvices {
    private static FirebaseSarvices instance;
    private FirebaseAuth auth;
    private FirebaseFirestore fire;
    private FirebaseStorage storage;

    public FirebaseSarvices() {
        auth = FirebaseAuth.getInstance();
        fire = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FirebaseAuth getAuth() {
        return auth;
    }



    public static FirebaseSarvices getInstance(){
        if (instance == null){
            instance= new FirebaseSarvices();
        }
        return instance;
    }
}
}
