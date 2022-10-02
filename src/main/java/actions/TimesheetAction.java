package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.TimesheetView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.TimesheetService;

public class TimesheetAction extends ActionBase {
    private TimesheetService service;

    /**
     * メソッドを実行する
     */@Override
     public void process() throws ServletException, IOException {

         service = new TimesheetService();

         //メソッドを実行
         invoke();
         service.close();
     }
     /**
      * 一覧画面を表示する
      * @throws ServletException
      * @throws IOException
      */
     public void index() throws ServletException, IOException {

         //指定されたページ数の一覧画面に表示する勤怠データを取得
         int page = getPage();
         List<TimesheetView> timesheets = service.getAllPerPage(page);

         //全日報データの件数を取得
         long timesheetsCount = service.countAll();

         putRequestScope(AttributeConst.TIMESHEETS, timesheets); //取得した勤怠データ
         putRequestScope(AttributeConst.TIM_COUNT, timesheetsCount); //全ての日報データの件数
         putRequestScope(AttributeConst.PAGE, page); //ページ数
         putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

         //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
         String flush = getSessionScope(AttributeConst.FLUSH);
         if (flush != null) {
             putRequestScope(AttributeConst.FLUSH, flush);
             removeSessionScope(AttributeConst.FLUSH);
         }

         //一覧画面を表示
         forward(ForwardConst.FW_TIM_INDEX);
     }
     /**
      * 新規登録画面を表示する
      * @throws ServletException
      * @throws IOException
      */
     public void entryNew() throws ServletException, IOException {

         putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

         //勤怠情報の空インスタンスに、勤怠の日付＝今日の日付を設定する
         TimesheetView tv = new TimesheetView();
         tv.setAttendance_Date(LocalDate.now());
         putRequestScope(AttributeConst.TIMESHEET, tv); //日付のみ設定済みの日報インスタンス

         //新規登録画面を表示
         forward(ForwardConst.FW_TIM_NEW);

     }
     /**
      * 新規登録を行う
      * @throws ServletException
      * @throws IOException
      */
     public void create() throws ServletException, IOException {

         //CSRF対策 tokenのチェック
         if (checkToken()) {

             //日報の日付が入力されていなければ、今日の日付を設定
             LocalDate day = null;
             if (getRequestParam(AttributeConst.TIM_DATE) == null
                     || getRequestParam(AttributeConst.TIM_DATE).equals("")) {
                 day = LocalDate.now();
             } else {
                 day = LocalDate.parse(getRequestParam(AttributeConst.TIM_DATE));
             }

             //出勤時間が入力されていなければ、9:00に設定
             LocalTime attendance = null;
             if (getRequestParam(AttributeConst.TIM_ATTENDANCE) == null
                     || getRequestParam(AttributeConst.TIM_ATTENDANCE).equals("")) {
                 attendance = LocalTime.of(9,00);
             } else {
                 attendance = LocalTime.parse(getRequestParam(AttributeConst.TIM_ATTENDANCE));
             }

             //退勤時間が入力されていなければ、18:00に設定
             LocalTime leaving = null;
             if (getRequestParam(AttributeConst.TIM_LEAVING) == null
                     || getRequestParam(AttributeConst.TIM_LEAVING).equals("")) {
                 leaving = LocalTime.of(18,00);
             } else {
                 leaving = LocalTime.parse(getRequestParam(AttributeConst.TIM_LEAVING));
             }


             //セッションからログイン中の従業員情報を取得
             EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

             //パラメータの値をもとに勤怠情報のインスタンスを作成する
             TimesheetView tv = new TimesheetView(
                     null,
                     ev, //ログインしている従業員を、日報作成者として登録する
                     day,
                     attendance,
                     leaving);

             //日報情報登録
             List<String> errors = service.create(tv);
             if (errors.size() > 0) {
                 //登録中にエラーがあった場合
                 putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                 putRequestScope(AttributeConst.TIMESHEET, tv);//入力された勤怠情報
                 putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                 //新規登録画面を再表示
                 forward(ForwardConst.FW_TIM_NEW);

             } else {
                 //登録中にエラーがなかった場合
                 //セッションに登録完了のフラッシュメッセージを設定
                 putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                 //一覧画面にリダイレクト
                 redirect(ForwardConst.ACT_TIM, ForwardConst.CMD_INDEX);
             }
         }
     }

}
