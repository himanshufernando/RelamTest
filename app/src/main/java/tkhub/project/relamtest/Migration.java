package tkhub.project.relamtest;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Himanshu on 3/16/2017.
 */

public class Migration implements RealmMigration{


    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        // Migrate from version 0 to version 1
        if (oldVersion == 0) {
            RealmObjectSchema personSchema = schema.get("User");

            // Combine 'firstName' and 'lastName' in a new field called 'fullName'
            personSchema
                    .addField("id", int.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {

                        }


                    })
            ;
            oldVersion++;
        }

        if (oldVersion == 1) {
            RealmObjectSchema personSchema = schema.get("User");

            // Combine 'firstName' and 'lastName' in a new field called 'fullName'
            personSchema
                    .addField("id", int.class, FieldAttribute.REQUIRED)
                    .addField("amount", int.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("id",25);
                            obj.set("amount",250);
                        }


                    })
            ;
            oldVersion++;
        }

        if (oldVersion == 2) {
            RealmObjectSchema personSchema = schema.get("User");

            // Combine 'firstName' and 'lastName' in a new field called 'fullName'
            personSchema
                    .addField("sex", int.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("sex",222);
                        }


                    })
            ;
            oldVersion++;
        }
        if (oldVersion == 3) {
            //this for if app doesn't change database structure and it's not harmful for olderversion data
            oldVersion++;
        }
    }
}
