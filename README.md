# TimeZoneServer
一个时间线形式的信息资源分享平台服务端
 
 
## API接口说明
> 账户相关接口

* 登陆 —— /account/login?username={username}&password={password} 

参数 | 说明
------|------------
username | 学号
**password** | **教务管理系统密码**

```JSON
{
    "status":200,
    "info":{
        "id":1,
        "stuId":"04121110",
        "stuName":"马化腾",
        "stuClass":"计科1204",
        "stuMajor":"计算机科学与技术",
        "stuIntroduction":"他还没有填写自我介绍哟~",
        "accessToken":"7f145147b41f03b24fc8cb9a0a1a34bd"
    }
}

{
    "status":500,
    "errorMessage":"登录失败，账号密码不匹配"
}
```

* 获取用户资料 —— /account/information?accessToken={accessToken}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证

```JSON
{
    "status":200,
    "info":{
        "id":1,
        "stuId":"04121110",
        "stuName":"马化腾",
        "stuClass":"计科1204",
        "stuMajor":"计算机科学与技术",
        "stuIntroduction":"他还没有填写自我介绍哟~",
        "accessToken":"7f145147b41f03b24fc8cb9a0a1a34bd"
    }
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 更新自我介绍 —— /account/introduction?accessToken={accessToken}&content={content}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
content | 自我介绍正文部分

```JSON
{
    "status":200,
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

> 动态模块相关接口

* 更新自我介绍 —— /notice/add?accessToken={accessToken}&content={content}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
content | 动态正文部分

```JSON
{
    "status":200,
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 获取所有动态 —— /notice/all?accessToken={accessToken}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证

```JSON
{
    "status":200,
    "notices":[
        {
            "id":1,
            "stuID":"04121110",
            "content":"哈哈哈哈hahahah哈哈哈哈哈哈哈",
            "date":1450266898000
        },
        {
            "id":2,
            "stuID":"04121110",
            "content":"换凤凰呼呼呼",
            "date":1450267242000
        }
        ...
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 获取一段动态信息 —— /notice/section?accessToken={accessToken}&skip={skip}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 skip | 获取一段信息的偏移量，每次返回10条记录

```JSON
{
    "status":200,
    "notices":[
        {
            "id":11,
            "stuID":"04121110",
            "content":"你丫儿傻逼了哈哈哈哈",
            "date":1450267302000
        },
        {
            "id":12,
            "stuID":"04121110",
            "content":"哈哈哈你来打我呀~",
            "date":1450267303000
        },
        ...
        {
            "id":20,
            "stuID":"04121110",
            "content":"你知道什么叫做逗逼么？",
            "date":1450267309000
        }
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 获取某一用户的一段动态信息 —— /notice/section_person?accessToken={accessToken}&stuID={stuID}&skip={skip}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 skip | 获取一段信息的偏移量，每次返回10条记录
 stuID | 学号

```JSON
{
    "status":200,
    "notices":[
            {
                "id":11,
                "stuID":"04121110",
                "content":"你丫儿傻逼了哈哈哈哈",
                "date":1450267302000
            },
            {
                "id":12,
                "stuID":"04121110",
                "content":"哈哈哈你来打我呀~",
                "date":1450267303000
            },
            ...
            {
                "id":20,
                "stuID":"04121110",
                "content":"你知道什么叫做逗逼么？",
                "date":1450267309000
            }
        ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 刷新动态信息 —— /notice/refresh?accessToken={accessToken}&lastID={lastID}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 lastID | 获得自此ID之后的所有动态信息

```JSON
{
    "status":200,
    "notices":[
        {
            "id":19,
            "stuID":"04121110",
            "content":"哈哈哈你来打我呀~",
            "date":1450267303000
        },
        {
            "id":20,
            "stuID":"04121110",
            "content":"你知道什么叫做逗逼么？",
            "date":1450267309000
        }
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

> 签到模块相关接口

* 签到到达 —— /sign/in?accessToken={accessToken}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证

```JSON
{
    "status":200,
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 签到离开 —— /sign/out?accessToken={accessToken}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证

```JSON
{
    "status":200,
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 以query形式获取历史签到情况(快捷方式) —— /sign/history_query?accessToken={accessToken}&query={query}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 query | 取值：day/today、week、month、year、yesterday、last_week、last_month、last_year

```JSON
{
    "status":200,
    "records":[
        {
            "id":1,
            "stuID":"04121110",
            "operation":1,
            "date":1450165769000
        },
        {
            "id":2,
            "stuID":"04121110",
            "operation":0,
            "date":1450165904000
        },
        {
            "id":3,
            "stuID":"04121110",
            "operation":1,
            "date":1450166318000
        }
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 获取历史签到情况(检索begin-end时间段之内的信息) —— /sign/history_time?accessToken={accessToken}&begin={begin}&end={end}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 begin | 检索开始时间  格式yyyy-MM-dd 如: 2015-12-16
 end | 检索结束时间  格式yyyy-MM-dd 如: 2015-12-16

```JSON
{
    "status":200,
    "records":[
        {
            "id":1,
            "stuID":"04121110",
            "operation":1,
            "date":1450165769000
        },
        {
            "id":2,
            "stuID":"04121110",
            "operation":0,
            "date":1450165904000
        },
        {
            "id":3,
            "stuID":"04121110",
            "operation":1,
            "date":1450166318000
        }
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 以query形式获取某人的历史签到情况(快捷方式) —— /sign/history_query?accessToken={accessToken}&query={query}&stuID={stuID}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 query | 取值：day/today、week、month、year、yesterday、last_week、last_month、last_year
 stuID | 学号

```JSON
{
    "status":200,
    "records":[
        {
            "id":1,
            "stuID":"04121110",
            "operation":1,
            "date":1450165769000
        },
        {
            "id":2,
            "stuID":"04121110",
            "operation":0,
            "date":1450165904000
        },
        {
            "id":3,
            "stuID":"04121110",
            "operation":1,
            "date":1450166318000
        }
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```

* 获取某人的历史签到情况(检索begin-end时间段之内的信息) —— /sign/history_time?accessToken={accessToken}&begin={begin}&end={end}&stuID={stuID}

参数 | 说明
------|------------
**accessToken** | 用户唯一标识，用其做身份验证
 begin | 检索开始时间  格式yyyy-MM-dd 如: 2015-12-16
 end | 检索结束时间  格式yyyy-MM-dd 如: 2015-12-16
 stuID | 学号

```JSON
{
    "status":200,
    "records":[
        {
            "id":1,
            "stuID":"04121110",
            "operation":1,
            "date":1450165769000
        },
        {
            "id":2,
            "stuID":"04121110",
            "operation":0,
            "date":1450165904000
        },
        {
            "id":3,
            "stuID":"04121110",
            "operation":1,
            "date":1450166318000
        }
    ]
}

{
    "status":500,
    "errorMessage":"AccessToken无效"
}
```
