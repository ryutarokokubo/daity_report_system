package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.timesheet;

/**
 * 日報データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class TimesheetConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param tv TimesheetViewのインスタンス
     * @return Timesheetのインスタンス
     */
    public static timesheet toModel(TimesheetView tv) {
        return new timesheet(
                tv.getId(),
                EmployeeConverter.toModel(tv.getEmployee()),
                tv.getAttendance_Date(),
                tv.getAttendance_time(),
                tv.getLeaving_time());
    }
    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Reportのインスタンス
     * @return ReportViewのインスタンス
     */
    public static TimesheetView toView(timesheet t) {

        if (t == null) {
            return null;
        }
        return new TimesheetView(
                t.getId(),
                EmployeeConverter.toView(t.getEmployee()),
                t.getAttendance_Date(),
                t.getAttendance_time(),
                t.getLeaving_time());
    }
    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<TimesheetView> toViewList(List<timesheet> list) {
        List<TimesheetView> evs = new ArrayList<>();

        for (timesheet t : list) {
            evs.add(toView(t));
        }

        return evs;
    }
    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(timesheet t, TimesheetView tv) {
        t.setId(tv.getId());
        t.setEmployee(EmployeeConverter.toModel(tv.getEmployee()));
        t.setAttendance_Date(tv.getAttendance_Date());
        t.setAttendance_time(tv.getAttendance_time());
        t.setLeaving_time(tv.getLeaving_time());
    }

}