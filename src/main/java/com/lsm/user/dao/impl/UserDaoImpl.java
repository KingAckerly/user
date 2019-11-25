package com.lsm.user.dao.impl;


import com.alibaba.druid.pool.DruidDataSource;
import com.lsm.user.base.BaseDao;
import com.lsm.user.dao.IUserDao;
import com.lsm.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

    /**
     * springboot通过配置文件已经自动装配了DruidDataSource相关属性,这里只需要注入即可
     */
    @Autowired
    private DruidDataSource druidDataSource;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void saveJDBC(UserEntity userEntity) {
        try {
            connection = druidDataSource.getConnection();
            String sql = "INSERT INTO USER (id, NAME)VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userEntity.getId());
            preparedStatement.setString(2, userEntity.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void removeJDBC(Integer id) {
        try {
            connection = druidDataSource.getConnection();
            String sql = "DELETE FROM user WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void updateJDBC(UserEntity userEntity) {
        try {
            connection = druidDataSource.getConnection();
            String sql = "UPDATE user SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userEntity.getName());
            preparedStatement.setInt(2, userEntity.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public UserEntity listJDBC() {
        try {
            //此处必须要通过druidDataSource获取数据库连接,否则druidDataSource监控不了sql
            connection = druidDataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println(id);
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }
}
