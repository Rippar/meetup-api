package by.murzo.meetup_api.repository.impl;

import by.murzo.meetup_api.entity.Meetup;
import by.murzo.meetup_api.repository.MeetupRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MeetupRepositoryImpl implements MeetupRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public MeetupRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Meetup> getAllMeetups() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Meetup", Meetup.class).getResultList();
    }

    @Override
    public Optional<Meetup> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Meetup.class, id));
    }

    @Override
    public void saveMeetup(Meetup meetup) {
        Session session = sessionFactory.getCurrentSession();
        session.save(meetup);
    }

    @Override
    public void updateMeetup(Meetup meetup) {
        Session session = sessionFactory.getCurrentSession();
        session.update(meetup);
    }

    @Override
    public void deleteMeetup(Meetup meetup) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(meetup);
    }

    @Override
    public List<Meetup> getFilteredMeetups(String topic, String organizer, LocalDateTime time, String sortedBy) {

        StringBuilder stringQuery = new StringBuilder("from Meetup ");
        boolean isFirst = true;

        if (topic != null) {
            stringQuery.append("where topic = :topic");
            isFirst = false;
        }

        if (organizer != null) {

            if (isFirst) {
                stringQuery.append(" where organizer = :organizer");
            } else {
                stringQuery.append(" and organizer = :organizer");
            }
            isFirst = false;
        }

        if (time != null) {

            if (isFirst) {
                stringQuery.append(" where time >= :time");
            } else {
                stringQuery.append(" and time >= :time");
            }
        }

        if (sortedBy != null) {
            stringQuery.append(" order by ").append(sortedBy);
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(stringQuery.toString(), Meetup.class);

        setParamsToQuery(query, topic, organizer, time);

        return query.getResultList();

    }

    private void setParamsToQuery(Query query, String topic, String organizer, LocalDateTime time) {

        if (topic != null) {
            query.setParameter("topic", topic);
        }

        if (organizer != null) {
            query.setParameter("organizer", organizer);
        }

        if (time != null) {
            query.setParameter("time", time);
        }
    }
}
