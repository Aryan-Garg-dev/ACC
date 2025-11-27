package competitive_coding;

public class LongestSequence {
  private char _target = '1';
  private String _binaryString = null;


  private LongestSequence(){}
  public LongestSequence(int binaryNumber, int targetBit){
    this._binaryString = Integer.toBinaryString(binaryNumber);
    this._target = Integer.toString(targetBit).charAt(0);
  }

  public static LongestSequence of(int targetBit){
    var ls = new LongestSequence();
    ls._target = Integer.toString(targetBit).charAt(0);
    return ls;
  }

  public LongestSequence in(int binaryNumber){
    this._binaryString = Integer.toBinaryString(binaryNumber);
    return this;
  }

  public int get(){
    if (this._binaryString == null|| this._binaryString.isEmpty()) return 0;

    int count = 0, maxCount = 0;

    for (char bit: this._binaryString.toCharArray()){
      if (bit == this._target){
        count++;
        maxCount = Math.max(count, maxCount);
      } else count = 0;
    }

    return maxCount;
  }

  public int getAfterFlip() {
    if (this._binaryString == null|| this._binaryString.isEmpty()) return 0;

    int count = 0, maxCount = 0, prevCount = 0;
    boolean otherBitPresent = false;

    for (char bit: this._binaryString.toCharArray()){
      if (bit == _target){
        count++;
      } else {
        otherBitPresent = true;
        maxCount = Math.max(maxCount, prevCount + count + 1);
        prevCount = count;
        count = 0;
      }
    }

    return otherBitPresent ? Math.max(maxCount, count + prevCount + 1) : count;
  }
}
