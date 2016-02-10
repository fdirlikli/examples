import com.speedment.config.parameters.*

name = "sampledb";
packageLocation = "src/main/java/";
packageName = "orwashere.speedment.dao";
enabled = true;
dbms {
    ipAddress = "localhost";
    name = "sampledb";
    port = 3306;
    typeName = "MySQL";
    username = "root";
    enabled = true;
    schema {
        columnCompressionType = ColumnCompressionType.NONE;
        fieldStorageType = FieldStorageType.WRAPPER;
        name = "sampledb";
        schemaName = "sampledb";
        storageEngineType = StorageEngineType.ON_HEAP;
        defaultSchema = false;
        enabled = true;
        table {
            columnCompressionType = ColumnCompressionType.INHERIT;
            fieldStorageType = FieldStorageType.INHERIT;
            name = "contacts";
            storageEngineType = StorageEngineType.INHERIT;
            tableName = "contacts";
            enabled = true;
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "id";
                typeMapper = com.speedment.internal.core.config.mapper.identity.IntegerIdentityMapper.class;
                autoincrement = true;
                enabled = true;
                nullable = false;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "firstname";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "lastname";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "telephone";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "email";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "created";
                typeMapper = com.speedment.internal.core.config.mapper.identity.TimestampIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = false;
            }
            primaryKeyColumn {
                name = "id";
                enabled = true;
            }
            index {
                name = "PRIMARY";
                enabled = true;
                unique = true;
                indexColumn {
                    name = "id";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
        }
    }
}