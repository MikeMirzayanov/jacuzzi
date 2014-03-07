package org.jacuzzi.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mike Mirzayanov
 */
class CachedTypeOracle<T> extends TypeOracle<T> {
    private static final ThreadLocal<Map<Class<?>, TypeOracle<?>>> threadLocalCache = new ThreadLocal<Map<Class<?>, TypeOracle<?>>>() {
        @Override
        protected Map<Class<?>, TypeOracle<?>> initialValue() {
            return new HashMap<Class<?>, TypeOracle<?>>();
        }
    };

    private final TypeOracle<T> typeOracle;

    TypeOracle<T> getTypeOracle() {
        return typeOracle;
    }

    CachedTypeOracle(Class<T> typeClass) {
        typeOracle = new TypeOracleImpl<T>(typeClass);
    }

    static Map<Class<?>, TypeOracle<?>> getThreadLocalCache() {
        return threadLocalCache.get();
    }

    @Override
    public String getIdColumn() {
        return typeOracle.getIdColumn();
    }

    @Override
    public String getTableName() {
        return typeOracle.getTableName();
    }

    @Override
    public T convertFromRow(Row row) {
        return typeOracle.convertFromRow(row);
    }

    @Override
    public List<T> convertFromRows(List<Row> rows) {
        return typeOracle.convertFromRows(rows);
    }

    @Override
    String getFieldList(boolean includeId, boolean useTablePrefix) {
        return typeOracle.getFieldList(includeId, useTablePrefix);
    }

    @Override
    String getValuesPatternListForInsert(boolean includeId, T instance) {
        return typeOracle.getValuesPatternListForInsert(includeId, instance);
    }

    @Override
    Object[] getValueListForInsert(boolean includeId, T instance) {
        return typeOracle.getValueListForInsert(includeId, instance);
    }

    @Override
    boolean hasReasonableId(T instance) {
        return typeOracle.hasReasonableId(instance);
    }

    @Override
    public String getQuerySetSql() {
        return typeOracle.getQuerySetSql();
    }

    @Override
    public String getQueryFindSql(String[] fields) {
        return typeOracle.getQueryFindSql(fields);
    }

    @Override
    Object[] getQuerySetArguments(T instance) {
        return typeOracle.getQuerySetArguments(instance);
    }

    @Override
    public void setIdValue(T instance, Object value) {
        typeOracle.setIdValue(instance, value);
    }

    @Override
    public Object getIdValue(T instance) {
        return typeOracle.getIdValue(instance);
    }

    @Override
    public T newInstance() {
        return typeOracle.newInstance();
    }
}
