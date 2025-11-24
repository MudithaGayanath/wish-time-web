// SignUp.jsx
import React, { useState } from 'react';

const SignUp = () => {

    const [currentStep, setCurrentStep] = useState(1);
    const [darkMode, setDarkMode] = useState(false);
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        userName: '',
        email: '',
        password: ''
    });
    const [selectedTaskTypes, setSelectedTaskTypes] = useState([]);

    const taskTypes = [
        { id: 1, name: 'Education', description: 'Learning, courses, and study tasks' },
        { id: 2, name: 'Sports', description: 'Fitness, workouts, and sports activities' },
        { id: 3, name: 'Dance', description: 'Dance practices and choreography' },
        { id: 4, name: 'Work', description: 'Professional tasks and projects' },
        { id: 5, name: 'Creative', description: 'Art, design, and creative projects' },
        { id: 6, name: 'Home', description: 'Household chores and maintenance' }
    ];

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleTaskTypeToggle = (taskTypeId) => {
        setSelectedTaskTypes(prev =>
            prev.includes(taskTypeId)
                ? prev.filter(id => id !== taskTypeId)
                : [...prev, taskTypeId]
        );
    };

    const handleNextStep = () => {
        // Basic validation for step 1
        if (currentStep === 1) {
            const { firstName, lastName, userName, email, password } = formData;
            if (!firstName || !lastName || !userName || !email || !password) {
                alert('Please fill in all required fields');
                return;
            }
        }
        setCurrentStep(2);
    };

    const handleBackStep = () => {
        setCurrentStep(1);
    };

    const handleSkip = () => {
        // Submit form without task preferences
        console.log('Form data:', formData);
        console.log('No task types selected');
        alert('Sign up completed without task preferences!');
    };

    const handleComplete = () => {
        // Submit form with selected task types
        console.log('Form data:', formData);
        console.log('Selected task types:', selectedTaskTypes);
        alert('Sign up completed with selected task types!');
    };

    const toggleDarkMode = () => {
        setDarkMode(!darkMode);
    };

    const isStepCompleted = (step) => {
        return currentStep > step;
    };

    const isCurrentStep = (step) => {
        return currentStep === step;
    };

    return (
        <div className={`min-h-screen ${darkMode ? 'dark' : ''}`}>
            <div className="bg-gray-50 dark:bg-gray-900 font-sans transition-colors duration-300">
                {/* Theme Toggle */}
                <div className="fixed top-4 right-4 z-10">
                    <button
                        onClick={toggleDarkMode}
                        className="bg-white dark:bg-gray-800 p-2 rounded-full shadow-md text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
                    >
                        {darkMode ? (
                            <i className="fas fa-sun"></i>
                        ) : (
                            <i className="fas fa-moon"></i>
                        )}
                    </button>
                </div>

                {/* Main Container */}
                <div className="min-h-screen flex items-center justify-center p-4">
                    <div className="w-full max-w-4xl bg-white dark:bg-gray-800 rounded-2xl shadow-xl overflow-hidden transition-colors duration-300">
                        <div className="flex flex-col md:flex-row">
                            {/* Left Side - Illustration/Info */}
                            <div className="md:w-2/5 bg-gradient-to-br from-primary-500 to-primary-700 p-8 text-white flex flex-col justify-center">
                                <div className="text-center mb-8">
                                    <h1 className="text-3xl font-bold mb-2">TaskFlow</h1>
                                    <p className="opacity-90">Organize your tasks, boost your productivity</p>
                                </div>
                                <div className="hidden md:block">
                                    <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6 mb-6">
                                        <h3 className="font-semibold text-lg mb-2">Step 1: Personal Info</h3>
                                        <p className="text-sm opacity-90">Create your account with basic information</p>
                                    </div>
                                    <div className="bg-white/10 backdrop-blur-sm rounded-xl p-6">
                                        <h3 className="font-semibold text-lg mb-2">Step 2: Task Preferences</h3>
                                        <p className="text-sm opacity-90">Select your preferred task categories (optional)</p>
                                    </div>
                                </div>
                            </div>

                            {/* Right Side - Form */}
                            <div className="md:w-3/5 p-8">
                                {/* Progress Steps */}
                                <div className="flex justify-center mb-8">
                                    <div className="flex items-center">
                                        <div className="flex flex-col items-center">
                                            <div className={`w-10 h-10 rounded-full flex items-center justify-center font-bold transition-all duration-300 ${
                                                isStepCompleted(1) || isCurrentStep(1)
                                                    ? 'bg-primary-500 text-white'
                                                    : 'bg-gray-300 text-gray-600'
                                            }`}>
                                                {isStepCompleted(1) ? (
                                                    <i className="fas fa-check"></i>
                                                ) : (
                                                    <span>1</span>
                                                )}
                                            </div>
                                            <span className="text-sm mt-1 text-gray-600 dark:text-gray-300">Personal Info</span>
                                        </div>
                                        <div className={`w-16 h-1 mx-2 transition-all duration-300 ${
                                            isStepCompleted(1) ? 'bg-primary-500' : 'bg-gray-300'
                                        }`}></div>
                                        <div className="flex flex-col items-center">
                                            <div className={`w-10 h-10 rounded-full flex items-center justify-center font-bold transition-all duration-300 ${
                                                isStepCompleted(1)
                                                    ? 'bg-primary-500 text-white'
                                                    : isCurrentStep(2)
                                                        ? 'bg-primary-500 text-white'
                                                        : 'bg-gray-300 text-gray-600'
                                            }`}>
                                                {isStepCompleted(1) ? (
                                                    <i className="fas fa-check"></i>
                                                ) : (
                                                    <span>2</span>
                                                )}
                                            </div>
                                            <span className="text-sm mt-1 text-gray-600 dark:text-gray-300">Task Types</span>
                                        </div>
                                    </div>
                                </div>

                                {/* Step 1: User Information Form */}
                                {currentStep === 1 && (
                                    <div className="transition-all duration-300">
                                        <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Create Your Account</h2>

                                        <form>
                                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                                                <div>
                                                    <label htmlFor="firstName" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                                        First Name
                                                    </label>
                                                    <input
                                                        type="text"
                                                        id="firstName"
                                                        name="firstName"
                                                        value={formData.firstName}
                                                        onChange={handleInputChange}
                                                        required
                                                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white transition-colors duration-300"
                                                    />
                                                </div>
                                                <div>
                                                    <label htmlFor="lastName" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                                        Last Name
                                                    </label>
                                                    <input
                                                        type="text"
                                                        id="lastName"
                                                        name="lastName"
                                                        value={formData.lastName}
                                                        onChange={handleInputChange}
                                                        required
                                                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white transition-colors duration-300"
                                                    />
                                                </div>
                                            </div>

                                            <div className="mb-4">
                                                <label htmlFor="userName" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                                    Username
                                                </label>
                                                <input
                                                    type="text"
                                                    id="userName"
                                                    name="userName"
                                                    value={formData.userName}
                                                    onChange={handleInputChange}
                                                    required
                                                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white transition-colors duration-300"
                                                />
                                            </div>

                                            <div className="mb-4">
                                                <label htmlFor="email" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                                    Email
                                                </label>
                                                <input
                                                    type="email"
                                                    id="email"
                                                    name="email"
                                                    value={formData.email}
                                                    onChange={handleInputChange}
                                                    required
                                                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white transition-colors duration-300"
                                                />
                                            </div>

                                            <div className="mb-6">
                                                <label htmlFor="password" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                                                    Password
                                                </label>
                                                <input
                                                    type="password"
                                                    id="password"
                                                    name="password"
                                                    value={formData.password}
                                                    onChange={handleInputChange}
                                                    required
                                                    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:text-white transition-colors duration-300"
                                                />
                                            </div>

                                            <div className="flex justify-end">
                                                <button
                                                    type="button"
                                                    onClick={handleNextStep}
                                                    className="px-6 py-2 bg-primary-500 text-white rounded-lg hover:bg-primary-600 focus:ring-2 focus:ring-primary-500 focus:ring-offset-2 transition-colors duration-300"
                                                >
                                                    Next <i className="fas fa-arrow-right ml-2"></i>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                )}

                                {/* Step 2: Task Type Selection */}
                                {currentStep === 2 && (
                                    <div className="transition-all duration-300">
                                        <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Select Your Task Preferences</h2>
                                        <p className="text-gray-600 dark:text-gray-400 mb-6">
                                            Choose the types of tasks you commonly work with. This step is optional.
                                        </p>

                                        <div className="grid grid-cols-2 sm:grid-cols-3 gap-4 mb-8">
                                            {taskTypes.map(taskType => (
                                                <div
                                                    key={taskType.id}
                                                    onClick={() => handleTaskTypeToggle(taskType.id)}
                                                    className={`border-2 rounded-xl p-5 text-center cursor-pointer transition-all duration-300 hover:scale-105 hover:border-primary-300 hover:shadow-md ${
                                                        selectedTaskTypes.includes(taskType.id)
                                                            ? 'border-primary-500 bg-primary-50 dark:bg-primary-900/20 scale-105 shadow-md'
                                                            : 'border-gray-200 dark:border-gray-700'
                                                    }`}
                                                >
                                                    <h3 className="font-medium text-gray-800 dark:text-white text-lg">
                                                        {taskType.name}
                                                    </h3>
                                                    <p className="text-sm text-gray-500 dark:text-gray-400 mt-2">
                                                        {taskType.description}
                                                    </p>
                                                </div>
                                            ))}
                                        </div>

                                        <div className="flex justify-between">
                                            <button
                                                type="button"
                                                onClick={handleBackStep}
                                                className="px-6 py-2 border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 transition-colors duration-300"
                                            >
                                                <i className="fas fa-arrow-left mr-2"></i> Back
                                            </button>

                                            <div className="flex space-x-3">
                                                <button
                                                    type="button"
                                                    onClick={handleSkip}
                                                    className="px-6 py-2 border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 transition-colors duration-300"
                                                >
                                                    Skip
                                                </button>

                                                <button
                                                    type="button"
                                                    onClick={handleComplete}
                                                    className="px-6 py-2 bg-primary-500 text-white rounded-lg hover:bg-primary-600 focus:ring-2 focus:ring-primary-500 focus:ring-offset-2 transition-colors duration-300"
                                                >
                                                    Complete Sign Up
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignUp;