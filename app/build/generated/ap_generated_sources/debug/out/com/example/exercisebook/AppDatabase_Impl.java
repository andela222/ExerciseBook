package com.example.exercisebook;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDAO _userDAO;

  private volatile ExerciseDayDAO _exerciseDayDAO;

  private volatile MeasurementDayDAO _measurementDayDAO;

  private volatile ExerciseDAO _exerciseDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `firstName` TEXT, `lastName` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ExerciseDay` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `date` INTEGER, FOREIGN KEY(`userId`) REFERENCES `User`(`Id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_ExerciseDay_userId` ON `ExerciseDay` (`userId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Exercise` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dayId` INTEGER NOT NULL, `name` TEXT, `numberOfSets` INTEGER, `weight` REAL, `repetitionsBySet` TEXT, FOREIGN KEY(`dayId`) REFERENCES `ExerciseDay`(`Id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_Exercise_dayId` ON `Exercise` (`dayId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MeasurementDay` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `date` INTEGER, `height` REAL, `weight` REAL, `shoulderWidth` REAL, `chestWidth` REAL, `waistWidth` REAL, `hipsWidth` REAL, `thighsWidth` REAL, `upperArmWidth` REAL, FOREIGN KEY(`userId`) REFERENCES `User`(`Id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_MeasurementDay_userId` ON `MeasurementDay` (`userId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9a915cf9968466c17a6551577d6570d1')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `ExerciseDay`");
        _db.execSQL("DROP TABLE IF EXISTS `Exercise`");
        _db.execSQL("DROP TABLE IF EXISTS `MeasurementDay`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(3);
        _columnsUser.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("firstName", new TableInfo.Column("firstName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("lastName", new TableInfo.Column("lastName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(com.example.exercisebook.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsExerciseDay = new HashMap<String, TableInfo.Column>(3);
        _columnsExerciseDay.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseDay.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseDay.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExerciseDay = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExerciseDay.add(new TableInfo.ForeignKey("User", "CASCADE", "NO ACTION",Arrays.asList("userId"), Arrays.asList("Id")));
        final HashSet<TableInfo.Index> _indicesExerciseDay = new HashSet<TableInfo.Index>(1);
        _indicesExerciseDay.add(new TableInfo.Index("index_ExerciseDay_userId", false, Arrays.asList("userId")));
        final TableInfo _infoExerciseDay = new TableInfo("ExerciseDay", _columnsExerciseDay, _foreignKeysExerciseDay, _indicesExerciseDay);
        final TableInfo _existingExerciseDay = TableInfo.read(_db, "ExerciseDay");
        if (! _infoExerciseDay.equals(_existingExerciseDay)) {
          return new RoomOpenHelper.ValidationResult(false, "ExerciseDay(com.example.exercisebook.ExerciseDay).\n"
                  + " Expected:\n" + _infoExerciseDay + "\n"
                  + " Found:\n" + _existingExerciseDay);
        }
        final HashMap<String, TableInfo.Column> _columnsExercise = new HashMap<String, TableInfo.Column>(6);
        _columnsExercise.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("dayId", new TableInfo.Column("dayId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("numberOfSets", new TableInfo.Column("numberOfSets", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("weight", new TableInfo.Column("weight", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("repetitionsBySet", new TableInfo.Column("repetitionsBySet", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercise = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExercise.add(new TableInfo.ForeignKey("ExerciseDay", "CASCADE", "NO ACTION",Arrays.asList("dayId"), Arrays.asList("Id")));
        final HashSet<TableInfo.Index> _indicesExercise = new HashSet<TableInfo.Index>(1);
        _indicesExercise.add(new TableInfo.Index("index_Exercise_dayId", false, Arrays.asList("dayId")));
        final TableInfo _infoExercise = new TableInfo("Exercise", _columnsExercise, _foreignKeysExercise, _indicesExercise);
        final TableInfo _existingExercise = TableInfo.read(_db, "Exercise");
        if (! _infoExercise.equals(_existingExercise)) {
          return new RoomOpenHelper.ValidationResult(false, "Exercise(com.example.exercisebook.Exercise).\n"
                  + " Expected:\n" + _infoExercise + "\n"
                  + " Found:\n" + _existingExercise);
        }
        final HashMap<String, TableInfo.Column> _columnsMeasurementDay = new HashMap<String, TableInfo.Column>(11);
        _columnsMeasurementDay.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("height", new TableInfo.Column("height", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("weight", new TableInfo.Column("weight", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("shoulderWidth", new TableInfo.Column("shoulderWidth", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("chestWidth", new TableInfo.Column("chestWidth", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("waistWidth", new TableInfo.Column("waistWidth", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("hipsWidth", new TableInfo.Column("hipsWidth", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("thighsWidth", new TableInfo.Column("thighsWidth", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMeasurementDay.put("upperArmWidth", new TableInfo.Column("upperArmWidth", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMeasurementDay = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMeasurementDay.add(new TableInfo.ForeignKey("User", "CASCADE", "NO ACTION",Arrays.asList("userId"), Arrays.asList("Id")));
        final HashSet<TableInfo.Index> _indicesMeasurementDay = new HashSet<TableInfo.Index>(1);
        _indicesMeasurementDay.add(new TableInfo.Index("index_MeasurementDay_userId", false, Arrays.asList("userId")));
        final TableInfo _infoMeasurementDay = new TableInfo("MeasurementDay", _columnsMeasurementDay, _foreignKeysMeasurementDay, _indicesMeasurementDay);
        final TableInfo _existingMeasurementDay = TableInfo.read(_db, "MeasurementDay");
        if (! _infoMeasurementDay.equals(_existingMeasurementDay)) {
          return new RoomOpenHelper.ValidationResult(false, "MeasurementDay(com.example.exercisebook.MeasurementDay).\n"
                  + " Expected:\n" + _infoMeasurementDay + "\n"
                  + " Found:\n" + _existingMeasurementDay);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9a915cf9968466c17a6551577d6570d1", "620a0191233676749c44cc2feb1161f3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "User","ExerciseDay","Exercise","MeasurementDay");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `ExerciseDay`");
      _db.execSQL("DELETE FROM `Exercise`");
      _db.execSQL("DELETE FROM `MeasurementDay`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UserDAO userDao() {
    if (_userDAO != null) {
      return _userDAO;
    } else {
      synchronized(this) {
        if(_userDAO == null) {
          _userDAO = new UserDAO_Impl(this);
        }
        return _userDAO;
      }
    }
  }

  @Override
  public ExerciseDayDAO exerciseDayDao() {
    if (_exerciseDayDAO != null) {
      return _exerciseDayDAO;
    } else {
      synchronized(this) {
        if(_exerciseDayDAO == null) {
          _exerciseDayDAO = new ExerciseDayDAO_Impl(this);
        }
        return _exerciseDayDAO;
      }
    }
  }

  @Override
  public MeasurementDayDAO measurementDayDao() {
    if (_measurementDayDAO != null) {
      return _measurementDayDAO;
    } else {
      synchronized(this) {
        if(_measurementDayDAO == null) {
          _measurementDayDAO = new MeasurementDayDAO_Impl(this);
        }
        return _measurementDayDAO;
      }
    }
  }

  @Override
  public ExerciseDAO exerciseDAO() {
    if (_exerciseDAO != null) {
      return _exerciseDAO;
    } else {
      synchronized(this) {
        if(_exerciseDAO == null) {
          _exerciseDAO = new ExerciseDAO_Impl(this);
        }
        return _exerciseDAO;
      }
    }
  }
}
