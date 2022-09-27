package services;

import java.util.List;

import actions.views.TimesheetConverter;
import actions.views.TimesheetView;
import models.timesheet;
import models.validators.TimesheetValidator;
/**
 * 日報テーブルの操作に関わる処理を行うクラス
 */
public class TimesheetService extends ServiceBase {
    /**
     * idを条件に取得したデータをTimesheetViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public TimesheetView findOne(int id) {
        return TimesheetConverter.toView(findOneInternal(id));
    }
    /**
     * 画面から入力された日報の登録内容を元にデータを1件作成し、日報テーブルに登録する
     * @param rv 日報の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<Integer> create(TimesheetView tv) {
        List<Integer> errors = TimesheetValidator.validate(tv);
        if (errors.size() == 0) {
            tv.setAttendance_Date(null);
            tv.setAttendance_time(null);
            tv.setLeaving_time(null);
        }
      //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }


    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private timesheet findOneInternal(int id) {
        return em.find(timesheet.class, id);
    }
    /**
     * 日報データを1件登録する
     * @param rv 日報データ
     */
    private void createInternal(TimesheetView tv) {

        em.getTransaction().begin();
        em.persist(TimesheetConverter.toModel(tv));
        em.getTransaction().commit();

    }
}
