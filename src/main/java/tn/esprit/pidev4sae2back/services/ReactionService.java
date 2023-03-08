package tn.esprit.pidev4sae2back.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Reaction;
import tn.esprit.pidev4sae2back.entities.ReactionEntity;
import tn.esprit.pidev4sae2back.repositories.ReactionRepository;

import tn.esprit.pidev4sae2back.entities.ReactionType;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@FieldDefaults(level = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ReactionService extends BaseService<Reaction, Long> {

    private final ReactionRepository reactionRepository;

    private final ForumServiceImp forumServiceImp;
    private final ThreadServiceImp threadServiceImp;

    public Reaction addReactionToForum(Reaction reaction, Long forumId) {
        reaction.setEntity(ReactionEntity.POST);
        reaction.setForum(forumServiceImp.findById(forumId).get());
        return reactionRepository.save(reaction);
    }

    public List<Reaction> getAllReactionsByForumId(Long forumId) {
        return reactionRepository.findAllByForumId(forumId);
    }

    public Optional<Reaction> getAllReactionsByForumIdAndOwner(Long forumId, Long userId) {
        return reactionRepository.findByForumIdAndOwner(forumId, userId);
    }
    public Reaction addReactionToThread(Reaction reaction,Long ThreadId){
        reaction.setEntity(ReactionEntity.COMMENT);
        reaction.setThread(threadServiceImp.findById(ThreadId).get());
        return reactionRepository.save(reaction);
    }
    public List<Reaction> getAllReactionsByThreadId(Long threadId){
        return reactionRepository.findAllByThreadId(threadId);
    }
     public Integer countAllByReactionTypeAndForumId(Long forumId, ReactionType reactionType){
          return reactionRepository.countAllByReactionTypeAndForumId(reactionType,forumId);
     }
    public Integer countAllByReactionTypeAndThreadId(Long threadId, ReactionType reactionType) {
        return reactionRepository.countAllByReactionTypeAndThreadId(reactionType, threadId);
    }
    public List<Reaction> getAllReactionsByThreadIdAndOwner(Long threadId, Long userId) {
        return reactionRepository.findAllByThreadIdAndOwner(threadId, userId);
    }
    public Map<String,Integer> getReactionCountByForumId(Long forumId){
        Map<String,Integer> map=new HashMap<>();
        for(ReactionType reactionType:ReactionType.values()){
            map.put(reactionType.name(),countAllByReactionTypeAndForumId(forumId,reactionType));
        }
        return map;
    }
    public Map<String,Integer> getReactionCountByThreadId(Long threadId){
        Map<String,Integer> map=new HashMap<>();
        for(ReactionType reactionType:ReactionType.values()){
            map.put(reactionType.name(),countAllByReactionTypeAndThreadId(threadId,reactionType));
        }
        return map;
    }
}
