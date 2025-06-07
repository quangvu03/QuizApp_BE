package com.example.service.impl;

import com.example.dtos.QuizDTO;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.entities.Account;
import com.example.entities.Favorite;
import com.example.entities.Quiz;
import com.example.repositories.FavoriteRepositoty;
import com.example.repositories.QuizRepository;
import com.example.repositories.UserRepository;
import com.example.repositories.QuizQuestionRepository;
import com.example.service.FavoriteService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class FavoriteImpl implements FavoriteService {

    @Inject
    private FavoriteRepositoty favoriteRepositoty;

    @Inject
    private QuizRepository quizRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private QuizQuestionRepository quizQuestionRepository;

    @Override
    public List<getListQuizDTO> findByUserId(long userId) {
        List<Favorite> favorites = favoriteRepositoty.findByUserId(userId);
        List<getListQuizDTO> quizDTOs = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Long quizId = favorite.getQuizId();
            Optional<Quiz> quizOptional = quizRepository.findById(quizId);

            if (quizOptional.isPresent()) {
                Quiz quiz = quizOptional.get();
                getListQuizDTO quizDTO = new getListQuizDTO();
                quizDTO.setId(quiz.getId());
                quizDTO.setTitle(quiz.getTitle());
                quizDTO.setImage(quiz.getImage());

                // Get number of questions for this quiz
                int questionCount = quizQuestionRepository.countByQuizId(quizId);
                quizDTO.setNumberquiz(questionCount);

                // Get user information
                Optional<Account> userOptional = userRepository.findById(quiz.getUserId());
                if (userOptional.isPresent()) {
                    Account user = userOptional.get();
                    quizDTO.setUserName(user.getUserName());
                    quizDTO.setImageUser(user.getAvatar());
                }

                quizDTOs.add(quizDTO);
            }
        }

        return quizDTOs;
    }

    @Override
    public Favorite addFavorite(Long quizId, Long userId) {
        // Check if the quiz exists
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (!quizOptional.isPresent()) {
            throw new RuntimeException("Quiz not found with id: " + quizId);
        }

        // Create a new favorite
        Favorite favorite = new Favorite();
        favorite.setQuizId(quizId);
        favorite.setUserId(userId);

        // Generate a unique ID (since there's no auto-generation strategy)
        // This is a simple approach; in a production environment, you might want to use a more robust ID generation strategy
        long maxId = 1;
        Iterable<Favorite> allFavorites = favoriteRepositoty.findAll();
        for (Favorite f : allFavorites) {
            if (f.getId() != null && f.getId() > maxId) {
                maxId = f.getId();
            }
        }
        favorite.setId(maxId + 1);

        // Save and return the favorite
        return favoriteRepositoty.save(favorite);
    }

    @Override
    public boolean deleteFavorite(Long userId, Long quizId) {
        // Find the favorite by userId and quizId
        Optional<Favorite> favoriteOptional = favoriteRepositoty.findByUserIdAndQuizId(userId, quizId);

        // If found, delete it and return true
        if (favoriteOptional.isPresent()) {
            favoriteRepositoty.delete(favoriteOptional.get());
            return true;
        }

        // If not found, return false
        return false;
    }

    @Override
    public boolean isQuizInUserFavorites(Long quizId, Long userId) {
        // Find the favorite by userId and quizId
        Optional<Favorite> favoriteOptional = favoriteRepositoty.findByUserIdAndQuizId(userId, quizId);

        // Return true if found, false otherwise
        return favoriteOptional.isPresent();
    }
}
