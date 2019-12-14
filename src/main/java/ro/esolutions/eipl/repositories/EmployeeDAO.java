package ro.esolutions.eipl.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.models.EmployeeModel;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAO {
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional
    public List<EmployeeModel> getEmployees(String value) {
        String searchProductQuery ="Select id,first_name,last_name,working_station,clothing_size,footwear_size,helmet_size from employees  where first_name like '%" + value+"%'";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        System.out.println(searchProductQuery);
        return namedParameterJdbcTemplate.query(searchProductQuery, mapSqlParameterSource,getSearchProductGridRowMapper());
    }
    private RowMapper<EmployeeModel> getSearchProductGridRowMapper() {
        return  (rs, rowNum) -> {
            EmployeeModel model = new EmployeeModel();
            model.setId(rs.getLong("id"));
            model.setClothingSize(rs.getString("clothing_size"));
            model.setHelmetSize(rs.getString("helmet_size"));
            model.setFootwearSize(rs.getString("footwear_size"));
            model.setWorkingStation(rs.getString("working_station"));
            model.setFirstName(rs.getString("first_name"));
            model.setLastName(rs.getString("last_name"));
            return model;
        };
    }
}
