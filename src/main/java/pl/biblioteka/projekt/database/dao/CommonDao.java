package pl.biblioteka.projekt.database.dao;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import pl.biblioteka.projekt.database.dbutils.DbManager;
import pl.biblioteka.projekt.database.models.BaseModel;
import pl.biblioteka.projekt.utils.FxmlUtils;
import pl.biblioteka.projekt.utils.exceptions.ApplicationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    private final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DbManager.getConnectionSource();
    }

    public <T extends BaseModel, I> void creatOrUpdate(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            // zalogowanie dokladnej wiadomosci o bledzie jaki wystepuje
            // utworzenie nowego bledu dla uzutkownika z wlasnej klasy z wyjatkiem
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.create.update"));
        } finally {
            closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.refresh"));
        } finally {
            closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationException {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deleteByID(Class<T> cls, Integer id) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        try {
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.delete"));
        } finally {
            closeDbConnection();
        }
    }

    public <T extends BaseModel, I> T findByID(Class<T> cls, Integer id) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        try {
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.no.found"));
        } finally {
            closeDbConnection();
        }
    }


    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {

        Dao<T, I> dao = getDao(cls);
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.not.found.all"));
        } finally {
            closeDbConnection();
        }
    }


    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        } finally {
            closeDbConnection();
        }
    }

    private void closeDbConnection() throws ApplicationException {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new ApplicationException(FxmlUtils.getResourceBundle().getString("error.get.dao"));
        }
    }

}