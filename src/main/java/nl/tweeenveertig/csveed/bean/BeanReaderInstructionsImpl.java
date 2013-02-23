package nl.tweeenveertig.csveed.bean;

import nl.tweeenveertig.csveed.api.BeanReaderInstructions;
import nl.tweeenveertig.csveed.api.LineReaderInstructions;
import nl.tweeenveertig.csveed.line.LineReaderInstructionsImpl;
import nl.tweeenveertig.csveed.report.CsvException;

import java.beans.PropertyEditor;

public class BeanReaderInstructionsImpl<T> implements BeanReaderInstructions<T> {

    private LineReaderInstructions lineReaderInstructions = new LineReaderInstructionsImpl();

    private BeanProperties properties;

    private Class<T> beanClass;

    private Class<? extends AbstractMappingStrategy> mappingStrategy = ColumnIndexStrategy.class;

    public BeanReaderInstructionsImpl(Class<T> beanClass) {
        this.beanClass = beanClass;
        this.properties = new BeanProperties(beanClass);
    }

    public Class<T> getBeanClass() {
        return this.beanClass;
    }

    public T newInstance() {
        try {
            return this.beanClass.newInstance();
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public LineReaderInstructions getLineReaderInstructions() {
        return this.lineReaderInstructions;
    }

    public BeanReaderInstructions<T> setUseHeader(boolean useHeader) {
        this.lineReaderInstructions.setUseHeader(useHeader);
        return this;
    }

    public BeanReaderInstructions<T> setStartRow(int startRow) {
        this.lineReaderInstructions.setStartRow(startRow);
        return this;
    }

    public BeanReaderInstructions<T> setEscape(char symbol) {
        this.lineReaderInstructions.setEscape(symbol);
        return this;
    }

    public BeanReaderInstructions<T> setQuote(char symbol) {
        this.lineReaderInstructions.setQuote(symbol);
        return this;
    }

    public BeanReaderInstructions<T> setSeparator(char symbol) {
        this.lineReaderInstructions.setSeparator(symbol);
        return this;
    }

    public BeanReaderInstructions<T> setEndOfLine(char[] symbols) {
        this.lineReaderInstructions.setEndOfLine(symbols);
        return this;
    }

    public BeanReaderInstructions<T> setMappingStrategy(Class<? extends AbstractMappingStrategy> mappingStrategy) {
        this.mappingStrategy = mappingStrategy;
        return this;
    }

    public BeanReaderInstructions<T> setRequired(String propertyName, boolean required) {
        this.getProperties().setRequired(propertyName, required);
        return this;
    }

    public BeanReaderInstructions<T> setConverter(String propertyName, PropertyEditor converter) {
        this.getProperties().setConverter(propertyName, converter);
        return this;
    }

    public BeanReaderInstructions<T> ignoreProperty(String propertyName) {
        this.getProperties().ignoreProperty(propertyName);
        return this;
    }

    public BeanReaderInstructions<T> mapIndexToProperty(int columnIndex, String propertyName) {
        this.getProperties().mapIndexToProperty(columnIndex, propertyName);
        return this;
    }

    public BeanReaderInstructions<T> mapNameToProperty(String columnName, String propertyName) {
        this.getProperties().mapNameToProperty(columnName, propertyName);
        return this;
    }

    public Class<? extends AbstractMappingStrategy> getMappingStrategy() {
        return this.mappingStrategy;
    }

    public BeanProperties getProperties() {
        return this.properties;
    }

    public BeanProperty getBeanPropertyWithName(String columnName) {
        return this.properties.fromName(columnName);
    }

    public BeanProperty getBeanPropertyWithIndex(int columnIndex) {
        return this.properties.fromIndex(columnIndex);
    }

    @SuppressWarnings("unchecked")
    public AbstractMappingStrategy<T> createMappingStrategy() {
        try {
            return this.mappingStrategy.newInstance();
        } catch (Exception err) {
            throw new CsvException("Unable to instantiate the mapping strategy", err);
        }
    }

}