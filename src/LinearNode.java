/**
 *  LinearNode.java       
 *  Represents a node in a linked list using generics
 * @param <T>
 */

public class LinearNode<TTTRecord>
{
  private LinearNode<TTTRecord> next;
  private TTTRecord record;
 
  /**
    Creates an empty node.
   */
  public LinearNode()
  {
    next = null;
    record = null;
  }
 
  /**
    Creates a node storing the specified element.
   */
  public LinearNode (TTTRecord elem)
  {
    next = null;
    record = elem;
  }
 
  /**
    Returns the node that follows this one.
   */
  public LinearNode<TTTRecord> getNext()
  {
    return next;
  }
 
  /**
    Sets the node that follows this one.
   */
  public void setNext (LinearNode<TTTRecord> node)
  {
    next = node;
  }
 
  /**
    Returns the element stored in this node.
   */
  public TTTRecord getRecord()
  {
    return record;
  }
 
  /**
    Sets the element stored in this node.
   */
  public void setRecord (TTTRecord elem)
  {
    record = elem;
  }
}