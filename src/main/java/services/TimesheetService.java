package services;

import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.TimesheetConverter;
import actions.views.TimesheetView;
import constants.JpaConst;
import models.timesheet;
import models.validators.TimesheetValidator;
/**
 * 勤怠テーブルの操作に関わる処理を行うクラス
 */
public class TimesheetService extends ServiceBase {
    /**
     * 指定した従業員が作成した勤怠データを、指定されたページ数の一覧画面に表示する分取得しTimesheetViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<TimesheetView> getMinePerPage(EmployeeView employee, int page) {

        List<timesheet> timesheets = em.createNamedQuery(JpaConst.Q_TIM_GET_ALL_MINE, timesheet.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return TimesheetConverter.toViewList(timesheets);
    }
    /**
     * 指定した従業員が作成した勤怠データの件数を取得し、返却する
     * @param employee
     * @return 勤怠データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_TIM_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }
    /**
     * 指定されたページ数の一覧画面に表示する日報データを取得し、TimesheetViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<TimesheetView> getAllPerPage(int page) {

        List<timesheet> timesheets = em.createNamedQuery(JpaConst.Q_TIM_GET_ALL, timesheet.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return TimesheetConverter.toViewList(timesheets);
    }
    /**
     * 勤怠テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long timesheets_count = (long) em.createNamedQuery(JpaConst.Q_TIM_COUNT, Long.class)
                .getSingleResult();
        return timesheets_count;
    }
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
    public List<String> create(TimesheetView tv) {
        List<String> errors = TimesheetValidator.validate(tv);
        if (errors.size() == 0) {
            createInternal(tv);
        }
      //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }
    /**
     * 画面から入力された日報の登録内容を元に、日報データを更新する
     * @param rv 日報の更新内容
     * @return バリデーションで発生したエラーのリスト

    public List<String> update(TimesheetView tv) {

        //バリデーションを行う
        List<String> errors = TimesheetValidator.validate(tv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            rv.setUpdatedAt(ldt);

            updateInternal(tv);
        }
        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }
  */
    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private timesheet findOneInternal(int id) {
        return em.find(timesheet.class, id);
    }
    /**
     * 勤怠データを1件登録する
     * @param tv 勤怠データ
     */
    private void createInternal(TimesheetView tv) {

        em.getTransaction().begin();
        em.persist(TimesheetConverter.toModel(tv));
        em.getTransaction().commit();


    }
}
