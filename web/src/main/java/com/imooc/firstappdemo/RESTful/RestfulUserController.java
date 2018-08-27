package com.imooc.firstappdemo.RESTful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Restful风格编码，简单的来说用一个url来处理同一个资源的相关操作，如增删改查等。
 */

@RestController
@RequestMapping("/restful/user")
public class RestfulUserController {
    //将user存放在map中，模拟数据库
    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    /**
     * 返回所有用户
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUserList() {
        //处理restful/user 的GET请求，用来获取用户列表
        //还可以通过@RequestParam从页面中传递参数来进行条件查询或者反野信息的传递
        List<User> userList = new ArrayList<User>(users.values());
        printMap();
        return userList;
    }

    /**
     * 返回特定ID的用户
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        System.out.println(id);
        printMap();
        return users.get(id);
    }

    /**
     * 创建新用户
     * @param user
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(User user) {
        // 处理"/users/"的POST请求，用来创建User
        //@ModelAttribute User user
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        printMap();
        return "success";
    }

    /**
     * 使用put更新用户
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, User user) {
        //处理"/restful/user"的put请求，用来获取url中id的User信息
        User u = users.get(id);
        u.setName(user.getName());
        users.put(id, u);
        printMap();
        return "success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id){
        // 处理"/users/{id}"的DELETE请求，用来删除User
        // url中的id可通过@PathVariable绑定到函数的参数中
        users.remove(id);
        printMap();
        return"success";
    }
    private void printMap(){
        for (Map.Entry<Long,User> entry:users.entrySet()){
            System.out.println(entry.getValue().toString());
        }
    }
}
