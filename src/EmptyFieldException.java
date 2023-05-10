/**
 * custom exception for Cases where any variable is Empty or in case of numbers 0
 */
class EmptyFieldException extends Exception{
    /**
     *
     * @param errorMessage the text of error
     */
    public EmptyFieldException(String errorMessage){
        super(errorMessage);
    }

}
