package com.demo.springboot.redissionwithspringboot.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.io.IOException;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 由Redisson默认的编码器为JsonJacksonCodec，JsonJackson在序列化有双向引用的对象时，会出现无限循环异常。而fastjson在检查出双向引用后会自动用引用符$ref替换，终止循环。
 * 在我的情况中，我序列化了一个service，这个service已被spring托管，而且和另一个service之间也相互注入了，用fastjson能 正常序列化到redis，而JsonJackson则抛出无限循环异常。
 * 为了序列化后的内容可见，所以不用redission其他自带的二进制编码器，自行实现编码器：
 * @date 2021/12/6 11:24
 * @see
 */
public class FastjsonCodec extends BaseCodec {

    private final Encoder encoder = new Encoder() {
        @Override
        public ByteBuf encode(Object in) throws IOException {
            ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
            try {
                ByteBufOutputStream os = new ByteBufOutputStream(out);
                // fastjson 版本要正确，低版本没这个方法（1.2.8）
                JSON.writeJSONString(os, in, SerializerFeature.WriteClassName);
                return os.buffer();
            } catch (IOException e) {
                out.release();
                throw e;
            } catch (Exception e) {
                out.release();
                throw new IOException(e);
            }
        }
    };
    private final Decoder<Object> decoder = (buf, state) -> {
        return JSON.parseObject(new ByteBufInputStream(buf), Object.class);
    };

    @Override
    public Decoder<Object> getValueDecoder() {
        return decoder;
    }

    @Override
    public Encoder getValueEncoder() {
        return encoder;
    }
}
