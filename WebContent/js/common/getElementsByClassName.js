function getElementsByClassName(className)
{
    var matchingItems = [];
    var allElements = document.getElementsByTagName("*");

    for(var i=0; i < allElements.length; i++)
    {
        if(allElements [i].className == className)
        {
            matchingItems.push(allElements[i]);
        }
    }

    return matchingItems;
}