package util;

/**
 * Created by lulu on 2018-06-28 17:25.
 */
public enum TradeStatus {

    NULL(-1, "占位"),
    NEW(11, "新建"),
    TO_CUTOFF(31, "待日切"),
    TO_APPLY(41, "待交易申请"),
    WAITING_APPLY_RESULT(42, "等待交易申请结果"),
    TO_GATHER_FUND(51, "待归集资金"),
    TO_CANCEL_PAYMENT(52, "待取消支付"),
    WAITING_ESTABLISH_RESULT(61, "等待产品成立结果"),
    TO_REPAY_FUND(71, "待兑付资金"),
    WAITING_REPAY_RESULT(72, "等待兑付资金结果"),
    SUCCESS(90, "订单成功"),
    FAIL(91, "订单失败");

    private int value;
    private String desc;

    TradeStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }
}
