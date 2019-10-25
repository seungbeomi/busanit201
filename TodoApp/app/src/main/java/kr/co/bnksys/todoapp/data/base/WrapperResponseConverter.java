package kr.co.bnksys.todoapp.data.base;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class WrapperResponseConverter<T> implements Converter<ResponseBody, T> {

    private Converter<ResponseBody, WrapperResponse<T>> converter;

    public WrapperResponseConverter(Converter<ResponseBody, WrapperResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrapperResponse<T> response = converter.convert(value);
        if (!response.getSuccess()) {
            // RxJava will call onError with this exception
            throw new WrapperError(response.getStatus());
        }

        return response.getData(); // TODO 데이터가 안들어오네...
    }
}
