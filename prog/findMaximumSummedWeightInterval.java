public Interval findMaximumSummedWeightInterval(
        List<SimplifiedExecution> executions, LocalDate targetDate){
    List<TimePoint> timePoints = new ArrayList<>();
    for(SimplifiedExecution execution : executions){
        TimePoint startTimePoint =
            new TimePoint(execution.getInitialTime().toLocalTime(),
            true, execution.getWeight());
        timePoints.add(startTimePoint);
        TimePoint endTimePoint =
            new TimePoint(execution.getFinalTime().toLocalTime(),
            false, execution.getWeight());
        timePoints.add(endTimePoint);
    }
    Collections.sort(timePoints);
    boolean bestStartPointFound = false;
    TimePoint bestStartPoint = null, bestEndPoint = null;
    double sum = 0.0, bestSum = 0.0;
    for(TimePoint timePoint : timePoints){
        if(timePoint.isStartPoint()){
            sum+=timePoint.getWeight();
            if(sum > bestSum){
                bestStartPoint = timePoint;
                bestStartPointFound = true;
                bestSum = sum;
            }
        }
        else {
            if(bestStartPointFound){
                bestEndPoint = timePoint;
                bestStartPointFound = false;
            }
            sum-=timePoint.getWeight();
        }
    }
    if(bestStartPoint == null || bestEndPoint == null){
        return null;
    }
    return new Interval(targetDate.toDateTime(bestStartPoint.getTime()),
        targetDate.toDateTime(bestEndPoint.getTime()));
}