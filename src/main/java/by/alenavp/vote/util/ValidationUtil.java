package by.alenavp.vote.util;

import by.alenavp.vote.exception.ErrorType;
import by.alenavp.vote.exception.NotFoundException;
import by.alenavp.vote.model.AbstractBaseEntity;
import by.alenavp.vote.validate.HasId;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static <T extends AbstractBaseEntity> T checkExisting(T entity) {
        if (entity == null) {
            throw new NotFoundException("Not exist with this id");
        }
        return entity;
    }

    public static void checkUsing(boolean using) {
        if (using) {
            throw new UnsupportedOperationException("The entity is already in use");
        }
    }

    public static void checkExisting(boolean isExist) {
        if (!isExist) {
            throw new NotFoundException("Not exist with this id");
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, HttpServletRequest req, Exception e, boolean logStackTrace, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logStackTrace) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return rootCause;
    }
}
