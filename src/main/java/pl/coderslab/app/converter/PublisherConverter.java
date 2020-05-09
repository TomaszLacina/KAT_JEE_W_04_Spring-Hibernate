package pl.coderslab.app.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.app.dao.PublisherDao;
import pl.coderslab.app.entity.Publisher;


public class PublisherConverter implements Converter<String, Publisher> {

    @Autowired
    PublisherDao publisherDao;

    @Override
    public Publisher convert(String sourceId) {
        return publisherDao.findById(Long.parseLong(sourceId));
    }


}
