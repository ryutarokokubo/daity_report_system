package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.TimesheetView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリデーションを行うクラス
 */
public class TimesheetValidator {

    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(TimesheetView tv) {
        List<String> errors = new ArrayList<String>();

        //出勤時間のチェック
        String AttendanceError = validateAttendance(tv.getAttendance_time());
        if (!AttendanceError.equals("")) {
            errors.add(AttendanceError);
        }

        //退勤時間のチェック
        String LeavingError = validateLeaving(tv.getLeaving_time());
        if (!LeavingError.equals("")) {
            errors.add(LeavingError);
        }

        return errors;
    }
    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateAttendance(String attendance_time) {
        if (attendance_time == null || attendance_time.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateLeaving(String leaving_time) {
        if (leaving_time == null || leaving_time.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}