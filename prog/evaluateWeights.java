public void evaluateWeights(SimplifiedExecution targetExecution,
                    List<SimplifiedExecution> pastExecutions) {
    for (SimplifiedExecution pastExecution : pastExecutions) {
        LocalDate targetExecutionDate = targetExecution.getDate();
        LocalDate pastExecutionDate = pastExecution.getDate();
        double dayOfWeekDistance = DAY_OF_WEEK_DISTANCES
            [targetExecutionDate.getDayOfWeek() - 1]
            [pastExecutionDate.getDayOfWeek() - 1];
        double weekOfWeekyearDistance = WEEK_OF_WEEKYEAR_DISTANCES
            [targetExecutionDate.getWeekOfWeekyear() - 1]
            [pastExecutionDate.getWeekOfWeekyear() - 1];

        double dayOfWeekCloseness = 
            (MAX_DAY_OF_WEEK_DISTANCE - dayOfWeekDistance)
            /MAX_DAY_OF_WEEK_DISTANCE;
        double weekOfWeekyearCloseness =
            (MAX_WEEK_OF_WEEKYEAR_DISTANCE - weekOfWeekyearDistance)
            /MAX_WEEK_OF_WEEKYEAR_DISTANCE;
        double sameCategoryCloseness =
            targetExecution.getCategory()
            .equals(pastExecution.getCategory()) ? 1 : 0;
        double sameGoalCloseness =
            targetExecution.getGoal()
            .equals(pastExecution.getGoal()) ? 1 : 0;

        double weight =
            (DAY_OF_WEEK_WEIGHT * dayOfWeekCloseness +
            SAME_CATEGORY_WEIGHT * sameCategoryCloseness +
            SAME_GOAL_WEIGHT * sameGoalCloseness) *
            weekOfWeekyearCloseness;
        pastExecution.setWeight(weight);
    }
}