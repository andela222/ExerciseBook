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
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MeasurementDayDAO_Impl implements MeasurementDayDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MeasurementDay> __insertionAdapterOfMeasurementDay;

  private final EntityDeletionOrUpdateAdapter<MeasurementDay> __deletionAdapterOfMeasurementDay;

  private final EntityDeletionOrUpdateAdapter<MeasurementDay> __updateAdapterOfMeasurementDay;

  public MeasurementDayDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMeasurementDay = new EntityInsertionAdapter<MeasurementDay>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MeasurementDay` (`Id`,`userId`,`date`,`height`,`weight`,`shoulderWidth`,`chestWidth`,`waistWidth`,`hipsWidth`,`thighsWidth`,`upperArmWidth`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MeasurementDay value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        final Long _tmp;
        _tmp = DateRoomConverter.fromDate(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        if (value.getHeight() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindDouble(4, value.getHeight());
        }
        if (value.getWeight() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getWeight());
        }
        if (value.getShoulderWidth() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getShoulderWidth());
        }
        if (value.getChestWidth() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getChestWidth());
        }
        if (value.getWaistWidth() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getWaistWidth());
        }
        if (value.getHipsWidth() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindDouble(9, value.getHipsWidth());
        }
        if (value.getThighsWidth() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindDouble(10, value.getThighsWidth());
        }
        if (value.getUpperArmWidth() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindDouble(11, value.getUpperArmWidth());
        }
      }
    };
    this.__deletionAdapterOfMeasurementDay = new EntityDeletionOrUpdateAdapter<MeasurementDay>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MeasurementDay` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MeasurementDay value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfMeasurementDay = new EntityDeletionOrUpdateAdapter<MeasurementDay>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MeasurementDay` SET `Id` = ?,`userId` = ?,`date` = ?,`height` = ?,`weight` = ?,`shoulderWidth` = ?,`chestWidth` = ?,`waistWidth` = ?,`hipsWidth` = ?,`thighsWidth` = ?,`upperArmWidth` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MeasurementDay value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        final Long _tmp;
        _tmp = DateRoomConverter.fromDate(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        if (value.getHeight() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindDouble(4, value.getHeight());
        }
        if (value.getWeight() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getWeight());
        }
        if (value.getShoulderWidth() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getShoulderWidth());
        }
        if (value.getChestWidth() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getChestWidth());
        }
        if (value.getWaistWidth() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getWaistWidth());
        }
        if (value.getHipsWidth() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindDouble(9, value.getHipsWidth());
        }
        if (value.getThighsWidth() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindDouble(10, value.getThighsWidth());
        }
        if (value.getUpperArmWidth() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindDouble(11, value.getUpperArmWidth());
        }
        stmt.bindLong(12, value.getId());
      }
    };
  }

  @Override
  public void insert(final MeasurementDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMeasurementDay.insert(day);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MeasurementDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMeasurementDay.handle(day);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final MeasurementDay day) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMeasurementDay.handle(day);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<MeasurementDay>> getAllDaysByUserId(final long userId) {
    final String _sql = "SELECT Id, userId, date FROM MeasurementDay WHERE userId=? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"MeasurementDay"}, false, new Callable<List<MeasurementDay>>() {
      @Override
      public List<MeasurementDay> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<MeasurementDay> _result = new ArrayList<MeasurementDay>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final MeasurementDay _item;
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
            _item = new MeasurementDay(_tmpUserId,_tmpDate);
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
  public LiveData<MeasurementDay> getDayById(final long Id) {
    final String _sql = "SELECT Id, userId, date, height, weight, shoulderWidth, chestWidth, waistWidth, hipsWidth, thighsWidth, upperArmWidth  FROM MeasurementDay WHERE Id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, Id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"MeasurementDay"}, false, new Callable<MeasurementDay>() {
      @Override
      public MeasurementDay call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfHeight = CursorUtil.getColumnIndexOrThrow(_cursor, "height");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfShoulderWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "shoulderWidth");
          final int _cursorIndexOfChestWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "chestWidth");
          final int _cursorIndexOfWaistWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "waistWidth");
          final int _cursorIndexOfHipsWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "hipsWidth");
          final int _cursorIndexOfThighsWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "thighsWidth");
          final int _cursorIndexOfUpperArmWidth = CursorUtil.getColumnIndexOrThrow(_cursor, "upperArmWidth");
          final MeasurementDay _result;
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
            _result = new MeasurementDay(_tmpUserId,_tmpDate);
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _result.setId(_tmpId);
            final Double _tmpHeight;
            if (_cursor.isNull(_cursorIndexOfHeight)) {
              _tmpHeight = null;
            } else {
              _tmpHeight = _cursor.getDouble(_cursorIndexOfHeight);
            }
            _result.setHeight(_tmpHeight);
            final Double _tmpWeight;
            if (_cursor.isNull(_cursorIndexOfWeight)) {
              _tmpWeight = null;
            } else {
              _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
            }
            _result.setWeight(_tmpWeight);
            final Double _tmpShoulderWidth;
            if (_cursor.isNull(_cursorIndexOfShoulderWidth)) {
              _tmpShoulderWidth = null;
            } else {
              _tmpShoulderWidth = _cursor.getDouble(_cursorIndexOfShoulderWidth);
            }
            _result.setShoulderWidth(_tmpShoulderWidth);
            final Double _tmpChestWidth;
            if (_cursor.isNull(_cursorIndexOfChestWidth)) {
              _tmpChestWidth = null;
            } else {
              _tmpChestWidth = _cursor.getDouble(_cursorIndexOfChestWidth);
            }
            _result.setChestWidth(_tmpChestWidth);
            final Double _tmpWaistWidth;
            if (_cursor.isNull(_cursorIndexOfWaistWidth)) {
              _tmpWaistWidth = null;
            } else {
              _tmpWaistWidth = _cursor.getDouble(_cursorIndexOfWaistWidth);
            }
            _result.setWaistWidth(_tmpWaistWidth);
            final Double _tmpHipsWidth;
            if (_cursor.isNull(_cursorIndexOfHipsWidth)) {
              _tmpHipsWidth = null;
            } else {
              _tmpHipsWidth = _cursor.getDouble(_cursorIndexOfHipsWidth);
            }
            _result.setHipsWidth(_tmpHipsWidth);
            final Double _tmpThighsWidth;
            if (_cursor.isNull(_cursorIndexOfThighsWidth)) {
              _tmpThighsWidth = null;
            } else {
              _tmpThighsWidth = _cursor.getDouble(_cursorIndexOfThighsWidth);
            }
            _result.setThighsWidth(_tmpThighsWidth);
            final Double _tmpUpperArmWidth;
            if (_cursor.isNull(_cursorIndexOfUpperArmWidth)) {
              _tmpUpperArmWidth = null;
            } else {
              _tmpUpperArmWidth = _cursor.getDouble(_cursorIndexOfUpperArmWidth);
            }
            _result.setUpperArmWidth(_tmpUpperArmWidth);
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
