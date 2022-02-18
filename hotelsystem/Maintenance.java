//Developed by Jakub Pažej - 18260179@studentmail.ul.ie
public class Maintenance extends User
{
    @Override public void setUserName(String userName)
    {
        this.userName = userName;
    }
    @Override public String getUserName(){
        return userName;
    }
}