package io.jpress.model;

import io.jboot.db.annotation.Table;
import io.jpress.model.base.BaseMemberJoinedRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * Generated by JPress.
 */
@Table(tableName = "member_joined_record", primaryKey = "id")
public class MemberJoinedRecord extends BaseMemberJoinedRecord<MemberJoinedRecord> {

    private static final long serialVersionUID = 1L;

    public static final String JOIN_TYPE_BUY = Member.SOURCE_BUY; //用户购买
    public static final String JOIN_TYPE_FREE = Member.SOURCE_FREE; //免费赠送
    public static final String JOIN_TYPE_OTHER = Member.SOURCE_OTHER; //其他

    public static final Map<String, String> joinTypeTexts = new HashMap<>();

    static {
        joinTypeTexts.put(JOIN_TYPE_BUY, "用户购买");
        joinTypeTexts.put(JOIN_TYPE_FREE, "免费赠送");
        joinTypeTexts.put(JOIN_TYPE_OTHER, "其他");
    }



    public static final int JOIN_FROM_ADMIN = 1;//后台管理员手动添加
    public static final int JOIN_FROM_BUY = 2;//用户自己在用户中心购买

    public static final Map<Integer, String> joinFromTexts = new HashMap<>();

    static {
        joinFromTexts.put(JOIN_FROM_ADMIN, "后台手动添加");
        joinFromTexts.put(JOIN_FROM_BUY, "用户前台自主购买");
    }


    public String getJoinTypeStr() {
        return joinTypeTexts.get(getJoinType());
    }

    public String getJoinFromStr() {
        return joinFromTexts.get(getJoinFrom());
    }




	
}
