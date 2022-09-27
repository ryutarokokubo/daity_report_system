package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.TimesheetView;
import constants.MessageConst;

/**
 * タイムシートインスタンスに設定されている値のバリデーションを行うクラス
 */
public class TimesheetValidator {

    /**
     * タイムシートインスタンスの各項目についてバリデーションを行う
     * @param tv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<Integer> validate(TimesheetView tv) {
        List<Integer> errors = new ArrayList<Integer>();

        //出勤時間のチェック
        int AttendanceError = validateAttendance(tv.getAttendance_time());
        if (AttendanceError>60||AttendanceError==null) {
            errors.add(AttendanceError);
        }

        //退勤時間のチェック
        int LeavingError = validateLeaving(tv.getLeaving_time());
        if (LeavingError>24||LeavingError==null) {
            errors.add(LeavingError);
        }

        return errors;
    }
    private static int validateAttendance(Integer attendance_time) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }
    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private int validateAttendance(int attendance) {
        if (attendance == null || attendance.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}