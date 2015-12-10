public class LocationDAO extends JdbcDaoSupport {

    Location location;
    List<Location> locations;

    public Location findById(long id) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM LOCATION WHERE id = ?";

        location = (Location) getJdbcTemplate().queryForObject(sql,
                new Object[]{id},
                new BeanPropertyRowMapper<Location>(Location.class));

        return location;
    }

    public List<Location> findAll() {
        String sql = "select * from location";

        locations = getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper<Location>(Location.class));

        return locations;
    }

    public long save(final Location location) {

        final String sql = "INSERT INTO LOCATION (name) VALUES(?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, location.getName());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();

    }


}
