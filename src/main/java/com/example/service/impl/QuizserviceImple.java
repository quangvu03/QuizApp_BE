package com.example.service.impl;

import com.example.configurations.QuizMapper;
import com.example.dtos.QuizDTO;
import com.example.dtos.reponseDTO.detailQuiz;
import com.example.dtos.reponseDTO.getListQuizDTO;
import com.example.dtos.reponseDTO.getListUserQuizDTO;
import com.example.entities.Account;
import com.example.entities.Channel;
import com.example.entities.Quiz;
import com.example.repositories.*;
import com.example.service.ChannelService;
import com.example.service.QuizService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Singleton
public class QuizserviceImple implements QuizService {

    @Inject
    QuizRepository quizRepository;

    @Inject
    QuizQuestionRepository quizQuestion;


    @Inject
    UserRepository userRepository;

    @Inject
    QuizMapper quizMapper;

    @Inject
    FavoriteRepositoty favoriteRepositoty;

    @Inject
    ChannelRepository channelRepository;


    @Override
    public List<getListQuizDTO> findAll() {
        List<Quiz> quizList = quizRepository.findAll();
        List<getListQuizDTO> resultList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            int numberOfQuestions = quizQuestion.countByQuizId(quiz.getId());
            Optional<Account> username = userRepository.findById(quiz.getUserId());
            Account account = username.get();
            getListQuizDTO dto = quizMapper.toGetListQuizDTO(quiz);
            dto.setId(quiz.getId());
            dto.setNumberquiz(numberOfQuestions);
            dto.setUserName(account.getUserName());
            dto.setTitle(quiz.getTitle());
            dto.setImage(quiz.getImage());
            dto.setImageUser(account.getAvatar());
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public List<getListUserQuizDTO> findAllbyUser() {
        List<Account> user = userRepository.findAll();
        List<getListUserQuizDTO> resultList = new ArrayList<>();
        for (Account account : user) {
            List<Channel> channelList = channelRepository.findByUserId(account.getId());
            Channel channel = channelList.get(0);
            getListUserQuizDTO dto = new getListUserQuizDTO();
            dto.setUsername(account.getUserName());
            dto.setNumberquiz(quizRepository.countByUserId(account.getId()));
            dto.setImage(account.getAvatar());
            dto.setChannelName(channel.getNameChanel());
            dto.setImagechanel(channel.getBackground());
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public detailQuiz getdetailQuiz(long idQuiz) {
        Quiz quiz = quizRepository.findById(idQuiz).orElse(null);
        long id = quiz.getId();
        Account account = userRepository.findById(quiz.getUserId()).orElse(null);
//        System.out.println("account: "+account);
        int numberquestion = quizQuestion.countByQuizId(id);
        long numberfavorite = favoriteRepositoty.countByQuizId(id);
        return new detailQuiz(id, quiz.getTitle(), quiz.getContent(), quiz.getCreatedAt(), quiz.getImage(), account.getUserName(), numberquestion, account.getAvatar(), numberfavorite);
    }

    @Override
    public List<getListQuizDTO> findQuizByName(String name) {
        List<Quiz> quizList ;
        quizList = quizRepository.searchByTitleLike(name);
        List<getListQuizDTO> resultList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            int numberOfQuestions = quizQuestion.countByQuizId(quiz.getId());
            Optional<Account> username = userRepository.findById(quiz.getUserId());
            Account account = username.get();
            getListQuizDTO dto = quizMapper.toGetListQuizDTO(quiz);
            dto.setId(quiz.getId());
            dto.setNumberquiz(numberOfQuestions);
            dto.setUserName(account.getUserName());
            dto.setTitle(quiz.getTitle());
            dto.setImage(quiz.getImage());
            dto.setImageUser(account.getAvatar());
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public List<getListUserQuizDTO> findQuizByUsername(String username) {
        List<Account> user = userRepository.findAccountsByUsernameContaining(username);
        List<getListUserQuizDTO> resultList = new ArrayList<>();
        for (Account account : user) {
            List<Channel> channelList = channelRepository.findByUserId(account.getId());
            Channel channel = channelList.get(0);
            int numberQuiz = quizRepository.countByUserId(account.getId());
            if (numberQuiz > 0) { // Only include if numberquiz is greater than 0
                getListUserQuizDTO dto = new getListUserQuizDTO();
                dto.setUsername(account.getUserName());
                dto.setNumberquiz(numberQuiz);
                dto.setImage(account.getAvatar());
                dto.setChannelName(channel.getNameChanel());
                dto.setImagechanel(channel.getBackground());
                resultList.add(dto);
            }
        }
        return resultList;
    }

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        Quiz quiz = quizMapper.toEntity(quizDTO);
        try {
            if (quiz.getTitle().isEmpty()) {
                throw new Exception("Tiêu đề rỗng");
            }
            if(quiz.getContent().isEmpty()){
                throw new Exception("Thiếu mô tả");
            }
            quiz.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            return quizMapper.toDTO(quizRepository.save(quiz));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Quiz> findByUserId(Long idUser) {
        return quizRepository.findByUserId(idUser);
    }

    @Override
    public List<getListQuizDTO> findQuizzesByUsername(String username) {
        Optional<Account> userOptional = userRepository.findByUserName(username);
        if (userOptional.isEmpty()) {
            return new ArrayList<>();
        }

        Account user = userOptional.get();
        List<Quiz> quizList = quizRepository.findByUserId(user.getId());
        List<getListQuizDTO> resultList = new ArrayList<>();

        for (Quiz quiz : quizList) {
            int numberOfQuestions = quizQuestion.countByQuizId(quiz.getId());
            getListQuizDTO dto = quizMapper.toGetListQuizDTO(quiz);
            dto.setId(quiz.getId());
            dto.setNumberquiz(numberOfQuestions);
            dto.setUserName(user.getUserName());
            dto.setTitle(quiz.getTitle());
            dto.setImage(quiz.getImage());
            dto.setImageUser(user.getAvatar());
            resultList.add(dto);
        }

        return resultList;
    }

    @Override
    public QuizDTO updateQuiz(QuizDTO quizDTO) {
        if (quizDTO.getId() == null ) {
            throw new IllegalArgumentException("ID quiz null");
        }
        if (quizDTO.getTitle() == null || quizDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề rỗng");
        }
        if (quizDTO.getContent() == null || quizDTO.getContent().isEmpty()) {
            throw new IllegalArgumentException("Thiếu mô tả");
        }

        int rowsUpdated = quizRepository.updateQuizById(
                quizDTO.getId(),
                quizDTO.getTitle(),
                quizDTO.getContent(),
                quizDTO.getImage()
        );

        if (rowsUpdated == 0) {
            throw new RuntimeException("Không cập nhật được quiz với id = " + quizDTO.getId());
        }

        Quiz updatedQuiz = quizRepository.findById(quizDTO.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quiz sau khi cập nhật"));
        QuizDTO result = new QuizDTO();
        result.setId(updatedQuiz.getId());
        result.setTitle(updatedQuiz.getTitle());
        result.setContent(updatedQuiz.getContent());
        result.setImage(updatedQuiz.getImage());
        System.out.println("updatedQuiz: " + result);
        return result;
    }

    @Override
    public void deleteQuiz(Long idQuiz) {
        if (idQuiz == null) {
            throw new IllegalArgumentException("ID quiz không được null");
        }

        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy quiz với id = " + idQuiz));

        quizRepository.deleteById(idQuiz);
    }




}
