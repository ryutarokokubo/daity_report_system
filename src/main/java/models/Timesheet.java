package models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * タイムシートのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_TIM)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_TIM_GET_ALL,
            query = JpaConst.Q_TIM_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_TIM_COUNT,
            query = JpaConst.Q_TIM_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_TIM_GET_ALL_MINE,
            query = JpaConst.Q_TIM_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_TIM_COUNT_ALL_MINE,
            query = JpaConst.Q_TIM_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Timesheet {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.TIM_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 勤怠を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.TIM_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * いつの勤怠かを示す日付
     */
    @Column(name = JpaConst.TIM_COL_TIM_DATE, nullable = false)
    private LocalDate attendance_Date;

    /**
     * 出勤時間
     */
    @Column(name = JpaConst.TIM_COL_ATTENDANCE, nullable = false)
    private LocalTime attendance_time;

    /**
     * 退勤時間
     */
    @Lob
    @Column(name = JpaConst.TIM_COL_LEAVING, nullable = false)
    private LocalTime leaving_time;

}