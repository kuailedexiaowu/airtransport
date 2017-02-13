package lut.lijihu.airtransport.user.service.impl;

import lut.lijihu.airtransport.user.domin.User;
import lut.lijihu.airtransport.user.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;


/**
 * Created by kj on 2017/2/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String getUser(String username,String password) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        User user=session.find(User.class,username);
        if(null==user){
            return "fail";
        }
        transaction.commit();
        session.close();
        String p=null;
        try{
        p=encode(password);}
        catch (Exception e){
           e.printStackTrace();
        }
        if(user.getPassword().equals(p))
            return "success";
        else
            return "fail";

    }

    private String encode(String password) throws Exception{

        byte[] bytes=password.getBytes("utf8");
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");
        messageDigest.update(bytes);
        byte[] bytes1=messageDigest.digest();
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = bytes1.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (byte byte0 : bytes1) {
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return  new String(str);
    }
}
