import React, {useState,useEffect} from 'react'
import MainHeading from './MainHeading';
import Paragraph from './Paragraph'
import api from './../../functions/api';
import { useNavigate } from 'react-router-dom';
function PageHeader({ 
  title = "Dashboard", 
  notificationCount = 0,
  onNotificationClick = () => {},
  onUserClick = () => {}
}) {
  const navigate = useNavigate();
  const [currentTime, setCurrentTime] = useState(new Date());
  const [userInitials,setUserInitials] = useState("");
  const [userName,setUserName] = useState("");
  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const formatDate = (date) => {
    return date.toLocaleDateString('en-US', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    });
  };

  const formatTime = (date) => {
    return date.toLocaleTimeString('en-US', {
      hour: '2-digit',
      minute: '2-digit',
      hour12: true
    });
  };

  const getGreeting = () => {
    const hour = currentTime.getHours();
    if (hour < 12) return 'Good morning';
    if (hour < 17) return 'Good afternoon';
    return 'Good evening';
  };

  async function getLoginUser() {
    try {
      const response = await api("user/loginUserController","GET");
      if(response.ok){
        const data = await response.json();
        setUserName(data.name);
        setUserInitials(data.init)
      }
    } catch (error) {
      
    }
  }

  async function logout() {
    try {
      const response = await api("user/loginUserController","DELETE");
      if(response.ok){
        navigate("/factory/sign-in");
      }
    } catch (error) {
      
    }
  }
useEffect(()=>{getLoginUser()},[]);
  return (
    <div className=" h-24 border-b border-gray-200 px-6 py-4 ">
      <div className="flex items-center justify-between">
        {/* Left side - Title and info */}
        <div className="flex flex-col space-y-2">
            <MainHeading title={title}/>
          {/* <h1 className="text-2xl font-bold text-gray-900">{title}</h1> */}
          
          <div className="flex items-center space-x-6 text-sm text-gray-600">
            {/* Date */}
            <div className="flex items-center space-x-2">
              <svg className="w-4 h-4 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
              <Paragraph text={formatDate(currentTime)}/>
              {/* <span>{formatDate(currentTime)}</span> */}
            </div>

            {/* Time */}
            <div className="flex items-center space-x-2">
              <svg className="w-4 h-4 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              {/* <span>{formatTime(currentTime)}</span> */}
              <Paragraph text={formatTime(currentTime)} />
            </div>

           

            {/* Greeting */}
            <div className="flex items-center space-x-2">
              <svg className="w-4 h-4 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
              <span>{getGreeting()}, {userName}!</span>
            </div>
          </div>
        </div>

        {/* Right side - Notification and User */}
        <div className="flex items-center space-x-4">
          {/* Notification Bell */}
          <button
            onClick={onNotificationClick}
            className="relative p-2 text-gray-400 hover:text-gray-600 hover:bg-gray-100 rounded-full transition-colors duration-200"
          >
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
            </svg>
            {notificationCount > 0 && (
              <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center">
                {notificationCount > 9 ? '9+' : notificationCount}
              </span>
            )}
          </button>

          {/* User Avatar */}
          <button
            onClick={logout}
            
            className="flex items-center justify-center w-10 h-10 bg-green-600 text-white font-semibold rounded-full hover:bg-green-700 transition-colors duration-200"
          >
            {userInitials}
          </button>
        </div>
      </div>
    </div>
  );
}

export default PageHeader