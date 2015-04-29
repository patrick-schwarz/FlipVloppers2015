package at.tugraz.flipvloppers.flipvloppers2015.model.items;

import java.util.Date;

/**
 * Created by Admin on 22.04.2015.
 */
public class Message {
    static int sid_ = 0;
    int id_;
    int idgroup_;
    int id_user_sender;
    int id_user_receiver;
    String text;
    Date create_time;
    int message_type_id;

    public Message()
    {
        id_ = sid_++;
        idgroup_ = 0;
        id_user_sender = 0;
        id_user_receiver = 0;
        message_type_id = 1;
        text = "Empty";
        create_time = new Date();
    }

    public int getMessage_type_id() {
        return message_type_id;
    }

    public void setMessage_type_id(int message_type_id) {
        this.message_type_id = message_type_id;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getIdgroup_() {
        return idgroup_;
    }

    public void setIdgroup_(int idgroup_) {
        this.idgroup_ = idgroup_;
    }

    public int getId_user_sender() {
        return id_user_sender;
    }

    public void setId_user_sender(int id_user_sender) {
        this.id_user_sender = id_user_sender;
    }

    public int getId_user_receiver() {
        return id_user_receiver;
    }

    public void setId_user_receiver(int id_user_receiver) {
        this.id_user_receiver = id_user_receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
