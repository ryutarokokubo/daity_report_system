package actions.views;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 勤怠情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class TimesheetView {

    /**
     * id
     */
    private Integer id;
    /**
     * 勤怠を登録した従業員
     */
    private EmployeeView employee;

    /**
     * いつの勤怠かを示す日付
     */
    private LocalDate attendance_Date;

    /**
     * 出勤時間
     */
    private LocalTime attendance_time;

    /**
     * 退勤時間
     */
    private LocalTime leaving_time;
}