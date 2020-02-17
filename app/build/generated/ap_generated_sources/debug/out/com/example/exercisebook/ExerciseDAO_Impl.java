package com.example.exercisebook;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExerciseDAO_Impl extends ExerciseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Exercise> __insertionAdapterOfExercise;

  private final EntityInsertionAdapter<ExerciseDay> __insertionAdapterOfExerciseDay;

  private final EntityDeletionOrUpdateAdapter<Exercise> __deletionAdapterOfExercise;

  private final EntityDeletionOrUpdateAdapter<Exercise> __updateAdapterOfExercise;

  public ExerciseDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExercise = new EntityInsertionAdapter<Exercise>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Exercise` (`Id`,`dayId`,`name`,`numberOfSets`,`weight`,`repetitionsBySet`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Exercise value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getDayId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getNumberOfSets() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getNumberOfSets());
        }
        if (value.getWeight() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getWeight());
        }
        final String _tmp;
        _tmp = SparseIntArrayConverter.fromSparseIntArray(value.repetitionsBySet);
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp);
        }
      }
    };
    this.__insertionAdapterOfExerciseDay = new EntityInsertionAdapter<ExerciseDay>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ExerciseDay` (`Id`,`userId`,`date`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ExerciseDay value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        final Long _tmp;
        _tmp = DateRoomConverter.fromDate(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
      }
    };
    this.__deletionAdapterOfExercise = new EntityDeletionOrUpdateAdapter<Exercise>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Exercise` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Exercise value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfExercise = new EntityDeletionOrUpdateAdapter<Exercise>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Exercise` SET `Id` = ?,`dayId` = ?,`name` = ?,`numberOfSets` = ?,`weight` = ?,`repetitionsBySet` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Exercise value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getDayId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getNumberOfSets() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getNumberOfSets());
        }
        if (value.getWeight() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getWeight());
        }
        final String _tmp;
        _tmp = SparseIntArrayConverter.fromSparseIntArray(value.repetitionsBySet);
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp);
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  void insert(final Exercise... exercises) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExercise.insert(exercises);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  long insert(final ExerciseDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfExerciseDay.insertAndReturnId(day);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  void delete(final Exercise exercise) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfExercise.handle(exercise);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  void update(final List<Exercise> exercises) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfExercise.handleMultiple(exercises);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void InsertAllWithParent(final ExerciseDay day, final List<Exercise> exercises) {
    __db.beginTransaction();
    try {
      ExerciseDAO_Impl.super.InsertAllWithParent(day, exercises);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  LiveData<List<Exercise>> getAllExercisesById(final long dayId) {
    final String _sql = "SELECT Id, dayId, name, numberOfSets, weight, repetitionsBySet FROM Exercise WHERE dayId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dayId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Exercise"}, false, new Callable<List<Exercise>>() {
      @Override
      public List<Exercise> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfDayId = CursorUtil.getColumnIndexOrThrow(_cursor, "dayId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNumberOfSets = CursorUtil.getColumnIndexOrThrow(_cursor, "numberOfSets");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfRepetitionsBySet = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitionsBySet");
          final List<Exercise> _result = new ArrayList<Exercise>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Exercise _item;
            _item = new Exercise();
            _item.Id = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDayId;
            _tmpDayId = _cursor.getLong(_cursorIndexOfDayId);
            _item.setDayId(_tmpDayId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final Integer _tmpNumberOfSets;
            if (_cursor.isNull(_cursorIndexOfNumberOfSets)) {
              _tmpNumberOfSets = null;
            } else {
              _tmpNumberOfSets = _cursor.getInt(_cursorIndexOfNumberOfSets);
            }
            _item.setNumberOfSets(_tmpNumberOfSets);
            final Double _tmpWeight;
            if (_cursor.isNull(_cursorIndexOfWeight)) {
              _tmpWeight = null;
            } else {
              _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            }
            _item.setWeight(_tmpWeight);
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfRepetitionsBySet);
            _item.repetitionsBySet = SparseIntArrayConverter.fromString(_tmp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
