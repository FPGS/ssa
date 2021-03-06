cmake_minimum_required(VERSION 3.6)
project(CplusplusModule)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        CmakeLists.txt test/testgtest.cpp test/segmentation.cpp test/smashing.cpp test/testgtest.h)
# Locate GTest

include_directories(${GTEST_INCLUDE_DIRS})

# Setup testing
enable_testing()


include(${CMAKE_HOME_DIRECTORY}/conanbuildinfo.cmake)
#include(${CMAKE_BINARY_DIR}/conanbuildinfo.cmake)
CONAN_BASIC_SETUP()

# Add test cpp file
add_executable( runUnitTests test/testgtest.cpp src/source/Savings.cpp src/include/Savings.h)
add_executable( runSegmentation test/segmentation.cpp src/source/Savings.cpp src/include/Savings.h)
add_executable( runSmashing test/smashing.cpp src/source/Savings.cpp src/include/Savings.h)
# Link test executable against gtest & gtest_main
target_link_libraries(runUnitTests ${CONAN_LIBS} ${GTEST_LIBRARIES} pthread)
target_link_libraries(runSegmentation ${CONAN_LIBS} ${GTEST_LIBRARIES} pthread)
target_link_libraries(runSmashing ${CONAN_LIBS} ${GTEST_LIBRARIES} pthread)
add_test( runUnitTests runUnitTests ${SOURCE_FILES} )
add_test( runSegmentation runSegmentation ${SOURCE_FILES} )
add_test( runSmashing runSegmentation ${SOURCE_FILES} )

