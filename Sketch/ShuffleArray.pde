class ShuffleArray extends SortingAlgorithm{

  @Override
  public void run() {
    // END THE RUNNING SORTING THREAD
    sortStart = false;

    try{ Thread.sleep(100); }
    catch (Exception e){}

    sortStart = true;
    // code taken from : http://bit.ly/2knN9Mh

    Random rnd = ThreadLocalRandom.current();

    int sleepTime = 2 * height / length;
    sleepTime = sleepTime < 1 ? 1 : sleepTime;

    for (int i = 0; i < v.length; i++) {

      try{ Thread.sleep(sleepTime); }
      catch (Exception e){}

      status[i] = 0;

      int index = rnd.nextInt(i + 1);
      SoundPlay(index);
      swap(index, i);
    }
    super.t = null;
    sortStart = false;
    comparisons = 0;
    arrayAccess = 0;
    return;
  }

  public void start(){
    super.start(this);
  }

}