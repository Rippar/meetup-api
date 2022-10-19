package by.murzo.meetup_api.repository.impl;

import by.murzo.meetup_api.entity.Meetup;
import by.murzo.meetup_api.repository.MeetupRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void addMeetup(Meetup meetup) {
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


}
