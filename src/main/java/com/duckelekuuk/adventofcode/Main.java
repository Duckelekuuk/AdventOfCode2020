package com.duckelekuuk.adventofcode;

import com.duckelekuuk.adventofcode.framework.ChallengeIndexer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger("AdventOfCode");

    public static void main(String[] args) {
        logger.setLevel(Level.INFO);

        ChallengeIndexer indexer = new ChallengeIndexer(logger);

        indexer.getChallenges();
    }
}
