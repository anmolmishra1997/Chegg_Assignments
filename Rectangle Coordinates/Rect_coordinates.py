def return_coordinates(image):
    #Copy the image to preserve original
    image = [x[:] for x in image]
    rectangle_coordinates = []
    rows = len(image)
    columns = len(image[0])
    
    for top_left_i in range(rows):				#The outer loop variable top_left_i iterates for in the list [0,1,....rows-1]
        for top_left_j in range(columns):		#The inner loop variable top_left_j iterates for in the list [0,1,....columns-1]
            if(image[top_left_i][top_left_j] == 0):		#This would be true at the top-left corner of every new rectangle
                #bottom_right_x and bottom_right_y will store the diagonal corner of this rectangle
                bottom_right_x = top_left_i 					
                bottom_right_y = top_left_j
                
                #This finds the row index of the bottom-right corner of rectangle
                while bottom_right_x<rows-1 and image[bottom_right_x+1][bottom_right_y]==0:
                    bottom_right_x+=1

                #This finds the column index of the bottom-right corner of rectangle
                while bottom_right_y<columns-1 and image[bottom_right_x][bottom_right_y+1] == 0:
                    bottom_right_y+=1
                
                #Convert all 0s in this rectangle to 1 to avoid double counting of this rectangle
                for m in range(top_left_i,bottom_right_x+1):
                    for n in range(top_left_j,bottom_right_y+1):
                        image[m][n] = 1
                
                #Add the obtained coordinates to the final list
                rectangle_coordinates.append([top_left_i,top_left_j,bottom_right_x,bottom_right_y])
    
    return rectangle_coordinates

def main():
    input_image = [
               [1,1,1,1,1,1,1],
               [1,1,1,1,1,1,1],
               [1,1,1,0,0,0,1],
               [1,0,1,0,0,0,1],
               [1,0,1,1,1,1,1],
               [1,0,1,0,0,1,1],
               [1,1,1,0,0,1,1],
               [1,1,1,1,1,1,1]
                ]
    print(return_coordinates(input_image))

if __name__ == "__main__":
    main()

