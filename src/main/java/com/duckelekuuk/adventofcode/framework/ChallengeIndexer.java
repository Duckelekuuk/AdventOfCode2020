package com.duckelekuuk.adventofcode.framework;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ClassPathUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ChallengeIndexer {

    private static final String CHALLENGE_PACKAGE = "com.duckelekuuk.adventofcode.challenges";

    @Getter
    private final Logger logger;

    public List<AbstractDayChallenge> getChallenges() {
        List<AbstractDayChallenge> challenges = new ArrayList<>();
        for (Class<? extends AbstractDayChallenge> challengeClass : getChallengeClasses()) {
            try {
                tryInstantiate(challengeClass).ifPresent(instance -> {
                    challenges.add(instance);
                    logger.log(Level.INFO, "registered: {0}", instance.getClass().getSimpleName());
                });
            }catch (InstantiationException | InvocationTargetException exception) {
                exception.printStackTrace();
            }
        }

        return challenges;
    }

    private Set<Class<? extends AbstractDayChallenge>> getChallengeClasses() {
        try {
            Set<Class<? extends AbstractDayChallenge>> classes = new HashSet<>();
            ClassLoader classLoader = getClass().getClassLoader();

            ImmutableSet<ClassPath.ClassInfo> classInfos = ClassPath.from(classLoader).getTopLevelClassesRecursive(CHALLENGE_PACKAGE);

            for (ClassPath.ClassInfo info : classInfos) {
                Class<?> aClass = Class.forName(info.getName());

                if (!AbstractDayChallenge.class.isAssignableFrom(aClass)) {
                    getLogger().log(Level.WARNING, "Found class: {0} that was not a challenge", aClass.getSimpleName());
                    continue;
                }

                Class<? extends AbstractDayChallenge> clazz = (Class<? extends AbstractDayChallenge>) aClass;
                classes.add(clazz);
            }

            return classes;
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return Collections.emptySet();
    }

    private <T> Optional<T> tryInstantiate(Class<T> clazz) throws InstantiationException, InvocationTargetException {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return Optional.of(constructor.newInstance());
        } catch (NoSuchMethodException | IllegalAccessException e2) {
            getLogger().warning("No constructor found for class " + clazz.getName() + ". Skipping..");
        }
        return Optional.empty();
    }


}
