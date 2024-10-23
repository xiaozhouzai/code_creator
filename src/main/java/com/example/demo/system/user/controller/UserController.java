package com.example.demo.system.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.common.Result;
import com.example.demo.system.user.domain.SysUsers;
import com.example.demo.system.user.domain.request.RegisterParam;
import com.example.demo.system.user.domain.request.UpdateParam;
import com.example.demo.system.user.domain.vo.LoginParam;
import com.example.demo.system.user.domain.vo.WeatherVo;
import com.example.demo.system.user.service.SysUsersService;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUsersService userService;

    // @Validated 启用校验注解
    // http://localhost:9999/api/user/login?username=lcy&password=158574
    @PostMapping("/login")
    public Result<String> doLogin(@RequestBody @Validated LoginParam loginParam) {
        String token = userService.login(loginParam);
        return Result.success(token);
    }


    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated RegisterParam registerParam) {
        userService.register(registerParam);
        return Result.success("注册成功");
    }
    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated RegisterParam registerParam) {
        userService.register(registerParam);
        return Result.success("添加成功");
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody @Validated UpdateParam updateParam) {
        userService.update(updateParam);
        return Result.success("修改成功");
    }

    @GetMapping("/delete")
    public Result<String> delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return Result.success("删除成功");
    }

    // http://localhost:9999/user/isLogin
    @GetMapping("/isLogin")
    public Result<String> isLogin() {
        return Result.success("当前会话是否登录：" + StpUtil.isLogin());
    }

    // http://localhost:9999/user/info
    @GetMapping("/info")
    public Result<SysUsers> userInfo() {
        SysUsers user = userService.getUserInfoById(Long.valueOf((String) StpUtil.getLoginId()));
        return Result.success(user);
    }

    // http://localhost:9999/api/user/weather
    @GetMapping("/weather")
    public Result<String> weather() {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL("https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=9f786445d7f83c5c6f77e9032055ad4a");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                if (null != inputStream) {
                    reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    String line = "";
                    while (null != (line = reader.readLine())) {
                        result.append(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭远程连接
            connection.disconnect();
        }
        Gson gson = new Gson();
        WeatherVo weatherVo = gson.fromJson(result.toString(), WeatherVo.class);
        String weather = weatherVo.getLives().get(0).get("weather");
        return Result.success(weather);
    }

    // http://localhost:9999/user/loginOut
    @GetMapping("/loginOut")
    public Result<String> loginOut() {
        StpUtil.logout();
        return Result.success("退出成功");
    }

}
