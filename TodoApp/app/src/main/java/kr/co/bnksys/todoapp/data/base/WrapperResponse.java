package kr.co.bnksys.todoapp.data.base;

public class WrapperResponse<T> {

    private T data;
    private Integer status;
    private Boolean success = false;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "WrapperResponse{" +
                "data=" + data +
                ", status=" + status +
                ", success=" + success +
                '}';
    }
}
