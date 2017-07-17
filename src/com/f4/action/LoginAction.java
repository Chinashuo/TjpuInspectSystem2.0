package com.f4.action;

/**
 * Created by yangxulei on 2017/7/6.
 */



        import java.io.IOException;
        import java.io.PrintWriter;

        import javax.servlet.http.HttpSession;

        import org.apache.struts2.ServletActionContext;
        import org.apache.struts2.convention.annotation.Action;
        import org.apache.struts2.convention.annotation.Namespace;
        import org.apache.struts2.convention.annotation.ParentPackage;

        import com.f4.dao.DBUtils;
        import com.f4.pojo.Login;
        import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("struts-default")
@Namespace("/")
public class LoginAction extends ActionSupport {
    String uname;
    String upwd;
    String vcode;
    String role;
    String returnResult="";

    @Action(value = "login")
    public void login() {
        System.out.print("aaaaa");
        try {
            ServletActionContext.getResponse().setCharacterEncoding("utf-8");
            PrintWriter out = ServletActionContext.getResponse().getWriter();

            HttpSession session = ServletActionContext.getRequest().getSession();

          //  String imgvcode = session.getAttribute("rand").toString();


                DBUtils dbutils=new DBUtils();
                Login login=new Login();
                login.setUname(uname);
                login.setUpwd(dbutils.makeMD5(upwd));
                login.setRole(role);
                boolean temp=dbutils.login(login);
                if(temp){
                    session.setAttribute("uname",uname);
                    session.setAttribute("role",role);
                    if(role.equals("管理员"))
                    {
                        returnResult = "adminLoginSuccess";
                        session.setAttribute("uname", uname);
                        session.setAttribute("result", returnResult);}
                    else{
                        returnResult = "plantLoginSuccess";}
                }else
                {
                    returnResult = "yonghuming or mima cuowu";
                }
                dbutils.close();

                out.print(returnResult);
            out.flush();
            out.close();

        } catch (IOException e) {

        }
//           if (vcode.equals(111)) {
//
//                DBUtils dbutils=new DBUtils();
//                Login login=new Login();
//                login.setUname(uname);
//                login.setUpwd(dbutils.makeMD5(upwd));
//                login.setRole(role);
//                boolean temp=dbutils.login(login);
//                if(temp){
//                    session.setAttribute("uname",uname);
//                    session.setAttribute("role",role);
//                    if(role.equals("管理员"))
//                    {
//                        returnResult = "adminLoginSuccess";
//                        session.setAttribute("uname", uname);
//                        session.setAttribute("result", returnResult);}
//                    else{
//                        returnResult = "plantLoginSuccess";}
//                }else
//                {
//                    returnResult = "yonghuming or mima cuowu";
//                }
//                dbutils.close();
//            } else {
//                returnResult = "yanzhengma Error";
//            }
//            out.print(returnResult);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//        }

    }
    @Action(value = "androidlogin")
    public void androidlogin() {
        try {
            ServletActionContext.getResponse().setCharacterEncoding("utf-8");
            PrintWriter out = ServletActionContext.getResponse().getWriter();
            HttpSession session = ServletActionContext.getRequest().getSession();
            DBUtils dbutils=new DBUtils();
            Login login=new Login();
            login.setUname(uname);
            login.setUpwd(dbutils.makeMD5(upwd));
            System.out.print("-------------"+login.getUname());
            boolean temp=dbutils.androidlogin(login);
            if(temp){
                session.setAttribute("uname",uname);
                session.setAttribute("role",role);
                returnResult = "登录成功" ;

            }else

            {
                returnResult = "登录失败";
            }
            dbutils.close();
            out.print(returnResult);
            out.flush();
            out.close();
        } catch (IOException e) {

        }

    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {

        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
}
