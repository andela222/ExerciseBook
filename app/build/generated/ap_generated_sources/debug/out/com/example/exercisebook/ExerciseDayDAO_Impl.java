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
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExerciseDayDAO_Impl implements ExerciseDayDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExerciseDay> __insertionAdapterOfExerciseDay;

  private final EntityDeletionOrUpdateAdapter<ExerciseDay> __deletionAdapterOfExerciseDay;

  private final EntityDeletionOrUpdateAdapter<ExerciseDay> __updateAdapterOfExerciseDay;

  public ExerciseDayDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExerciseDay = new EntityInsertionAdapter<ExerciseDay>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ExerciseDay` (`Id`,`userId`,`date`) VALUES (nullif(?, 0),?,?)";
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
    this.__deletionAdapterOfExerciseDay = new EntityDeletionOrUpdateAdapter<ExerciseDay>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ExerciseDay` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ExerciseDay value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfExerciseDay = new EntityDeletionOrUpdateAdapter<ExerciseDay>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ExerciseDay` SET `Id` = ?,`userId` = ?,`date` = ? WHERE `Id` = ?";
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
        stmt.bindLong(4, value.getId());
      }
    };
  }

  @Override
  public void insert(final ExerciseDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExerciseDay.insert(day);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ExerciseDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfExerciseDay.handle(day);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ExerciseDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfExerciseDay.handle(day);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ExerciseDay>> getAllDaysByUserId(final long userId) {
    final String _sql = "SELECT Id, userId, date FROM ExerciseDay WHERE userId=? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ExerciseDay"}, false, new Callable<List<ExerciseDay>>() {
      @Override
      public List<ExerciseDay> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<ExerciseDay> _result = new ArrayList<ExerciseDay>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ExerciseDay _item;
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateRoomConverter.toDate(_tmp);
            _item = new ExerciseDay(_tmpUserId,_tmpDate);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
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

  @Override
  public LiveData<ExerciseDay> getLatestInsertedExerciseDay(final long userId) {
    final String _sql = "SELECT Id, userId, date FROM ExerciseDay WHERE userId=? ORDER BY Id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ExerciseDay"}, false, new Callable<ExerciseDay>() {
      @Override
      public ExerciseDay call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final ExerciseDay _result;
          if(_cursor.moveToFirst()) {
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateRoomConverter.toDate(_tmp);
            _result = new ExerciseDay(_tmpUserId,_tmpDate);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _result.setId(_tmpId);
          } else {
            _result = null;
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
